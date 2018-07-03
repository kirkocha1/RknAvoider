package com.rknavoider.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.rknavoider.configs.GlobalConfig
import com.rknavoider.utils.ClickListener

class RknAvoiderWorld(var screenWidth: Float, var screenHeight: Float) : ClickListener {

    private val rknEnemies = mutableListOf<BigRknEnemy>()
    private var bullets = mutableListOf<Bullet>()
    private var telegramPlayer = TelegramPlayer(screenWidth / 2, screenHeight / 2, GlobalConfig.PLAYER_WIDTH, GlobalConfig.PLAYER_WIDTH)
    private val rknEnemiesToRemove = mutableListOf<BigRknEnemy>()
    private val bulletsToRemove = mutableListOf<Bullet>()
    private var nextBulletTime = GlobalConfig.BULLET_DELAY.toFloat()
    private val hitSoundEnemy = Gdx.audio.newSound(Gdx.files.internal("data/EnemyDestroyed.wav"))

    var score = 0
    val lifesCount get() = telegramPlayer.lifesCount

    init {
        createNewEnemies()
    }

    fun update(delta: Float) {
        Gdx.app.log("GameWorld", "update");
        telegramPlayer.update(delta)
        nextBulletTime -= delta
        for (bullet in bullets) {
            bullet.update(delta)
        }
    }

    fun render(delta: Float, batch: SpriteBatch) {
        renderEnemies(delta, batch)
        telegramPlayer.draw(batch)
        drawBullets(delta, batch)
        clearEnemies(rknEnemiesToRemove)
        clearBullets(bulletsToRemove)
    }

    fun isGameOver() = telegramPlayer.wasKilled

    override fun onClick(x: Float, y: Float) {
        telegramPlayer.onClick(x, y)
    }


    private fun clearBullets(bulletsToRemove: MutableList<Bullet>) {
        for (bullet in bulletsToRemove) {
            bullet.dispose()
            bullets.remove(bullet)
        }
    }

    private fun drawBullets(delta: Float, batch: SpriteBatch) {
        if (Gdx.input.isTouched && nextBulletTime < 0) {
            nextBulletTime = GlobalConfig.BULLET_DELAY.toFloat()
            bullets.add(com.rknavoider.models.Bullet(telegramPlayer.position.x + GlobalConfig.PLAYER_WIDTH / 2, telegramPlayer.position.y + GlobalConfig.PLAYER_WIDTH / 2))
        }

        for (bullet in bullets) {
            for (enemy in rknEnemies) {
                if (bullet.collisionRect.collidesWith(enemy.collisionRect)) {
                    hitSoundEnemy.play()
                    bulletsToRemove.add(bullet)
                    rknEnemiesToRemove.add(enemy)
                    score += 100
                }
            }
        }
        bullets.removeAll(bulletsToRemove)

        for (bullet in bullets) {
            bullet.draw(batch)
        }
    }

    private fun renderEnemies(delta: Float, batch: SpriteBatch) {
        createNewEnemies()
        for (bigEnemy in rknEnemies) {
            bigEnemy.update(delta)
            if (bigEnemy.remove) {
                rknEnemiesToRemove.add(bigEnemy)
            }
        }
        for (bigEnemy in rknEnemies) {
            bigEnemy.draw(batch)
        }
        checkForCollision()

    }

    private fun createNewEnemies() {
        val horizontalBounds = (screenWidth - GlobalConfig.BIG_ENEMY_WIDTH).toInt()
        val verticalBounds = (screenHeight / 2).toInt()
        if (rknEnemies.size < GlobalConfig.MAX_ENEMY_COUNT) {
            var countToAdd = GlobalConfig.MAX_ENEMY_COUNT - rknEnemies.size
            while (countToAdd > 0) {
                rknEnemies.add(com.rknavoider.models.BigRknEnemy(MathUtils.random.nextInt(horizontalBounds).toFloat(), screenHeight + MathUtils.random.nextInt(verticalBounds).toFloat()))
                countToAdd--
            }
        }
    }

    private fun checkForCollision() {
        for (bigEnemy in rknEnemies) {
            if (bigEnemy.collisionRect.collidesWith(telegramPlayer.playerRect)) {
                telegramPlayer.hit()
            }
        }
    }

    private fun clearEnemies(rknEnemies: MutableList<BigRknEnemy>) {
        for (enemy in rknEnemies) {
            enemy.dispose()
            this.rknEnemies.remove(enemy)
        }
    }

    fun dispose() {
        hitSoundEnemy.dispose()
        telegramPlayer.dispose()
        for (enemy in rknEnemies) {
            enemy.dispose()
        }
        for (bullet in bullets) {
            bullet.dispose()
        }
        clearEnemies(rknEnemiesToRemove)
        clearBullets(bulletsToRemove)
    }
}
