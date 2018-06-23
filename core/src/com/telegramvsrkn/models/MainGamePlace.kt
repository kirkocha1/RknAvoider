package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils
import com.telegramvsrkn.GlobalConfig

class MainGamePlace(var screenWidth: Float, var screenHeight: Float) {
    private val bigEnemies = mutableListOf<BigEnemy>()
    var telegramPlayer = TelegramPlayer(screenWidth / 2, screenHeight / 2, GlobalConfig.PLAYER_WIDTH, GlobalConfig.PLAYER_WIDTH)

    init {
        createNewEnemies()
    }

    fun update(delta: Float) {
        Gdx.app.log("GameWorld", "update");
        telegramPlayer.update(delta)
    }

    fun render(delta: Float, batch: SpriteBatch) {
        renderEnemies(delta, batch)
        telegramPlayer.draw(batch)
    }

    fun isGameOver() = telegramPlayer.wasKilled

    private fun renderEnemies(delta: Float, batch: SpriteBatch) {
        createNewEnemies()
        val rknEnemiesToRemove = mutableListOf<BigEnemy>()
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
        clearEnemies(rknEnemiesToRemove)
    }

    private fun createNewEnemies() {
        val horizontalBounds = (screenWidth - GlobalConfig.BIG_ENEMY_WIDTH).toInt()
        val verticalBounds = (screenHeight / 2).toInt()
        if (bigEnemies.size < GlobalConfig.MAX_ENEMY_COUNT) {
            var countToAdd = GlobalConfig.MAX_ENEMY_COUNT - bigEnemies.size
            while (countToAdd > 0) {
                bigEnemies.add(BigEnemy(MathUtils.random.nextInt(horizontalBounds).toFloat(), screenHeight + MathUtils.random.nextInt(verticalBounds).toFloat()))
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
