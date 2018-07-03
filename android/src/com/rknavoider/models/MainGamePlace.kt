package com.rknavoider.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.rknavoider.configs.GlobalConfig

class MainGamePlace(var screenWidth: Float, var screenHeight: Float) {
    private val bigEnemies = mutableListOf<BigEnemy>()
    private var bullets = mutableListOf<Bullet>()
    var telegramPlayer = TelegramPlayer(screenWidth / 2, screenHeight / 2, GlobalConfig.PLAYER_WIDTH, GlobalConfig.PLAYER_WIDTH)
    var score = 0
    val rknEnemiesToRemove = mutableListOf<BigEnemy>()
    val bulletsToRemove = mutableListOf<Bullet>()
    var nextBulletTime = GlobalConfig.BULLET_DELAY.toFloat()
    val hitSoundEnemy = Gdx.audio.newSound(Gdx.files.internal("data/EnemyDestroyed.wav"))

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

    private fun clearBullets(bulletsToRemove: MutableList<Bullet>) {
        for (bullet in bulletsToRemove) {
            bullets.remove(bullet)
        }
    }

    fun isGameOver() = telegramPlayer.wasKilled

    private fun drawBullets(delta: Float, batch: SpriteBatch) {
        if (Gdx.input.isTouched && nextBulletTime < 0) {
            nextBulletTime = GlobalConfig.BULLET_DELAY.toFloat()
            bullets.add(com.rknavoider.models.Bullet(telegramPlayer.position.x + GlobalConfig.PLAYER_WIDTH / 2, telegramPlayer.position.y + GlobalConfig.PLAYER_WIDTH / 2))
        }

        for (bullet in bullets) {
            for (enemy in bigEnemies) {
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
            bullet.render(batch)
        }
    }

    private fun renderEnemies(delta: Float, batch: SpriteBatch) {
        createNewEnemies()
        for (bigEnemy in bigEnemies) {
            bigEnemy.update(delta)
            if (bigEnemy.remove) {
                rknEnemiesToRemove.add(bigEnemy)
            }
        }
        for (bigEnemy in bigEnemies) {
            bigEnemy.draw(batch)
        }
        checkForCollision()

    }

    private fun createNewEnemies() {
        val horizontalBounds = (screenWidth - GlobalConfig.BIG_ENEMY_WIDTH).toInt()
        val verticalBounds = (screenHeight / 2).toInt()
        if (bigEnemies.size < GlobalConfig.MAX_ENEMY_COUNT) {
            var countToAdd = GlobalConfig.MAX_ENEMY_COUNT - bigEnemies.size
            while (countToAdd > 0) {
                bigEnemies.add(com.rknavoider.models.BigEnemy(MathUtils.random.nextInt(horizontalBounds).toFloat(), screenHeight + MathUtils.random.nextInt(verticalBounds).toFloat()))
                countToAdd--
            }
        }
    }

    private fun checkForCollision() {
        for (bigEnemy in bigEnemies) {
            if (bigEnemy.collisionRect.collidesWith(telegramPlayer.playerRect)) {
                telegramPlayer.hit()
            }
        }
    }


    private fun clearEnemies(enemies: MutableList<BigEnemy>) {
        for (enemy in enemies) {
            bigEnemies.remove(enemy)
        }
    }
}
