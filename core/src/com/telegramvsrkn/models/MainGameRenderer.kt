package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.MathUtils.random
import com.telegramvsrkn.GameOverScreen
import com.telegramvsrkn.GlobalConfig
import com.telegramvsrkn.ScrollingBackground
import com.telegramvsrkn.TelegramVsRknGame


class MainGameRenderer(
        private val game: TelegramVsRknGame,
        private val mainGamePlace: MainGamePlace,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    var cam = OrthographicCamera(screenWidth, screenHeight)
    private val batcher = SpriteBatch()
    private val background = ScrollingBackground(screenWidth, screenHeight)
    private val bigEnemies = mutableListOf<BigEnemy>()
    val mainThemeSound = Gdx.audio.newSound(Gdx.files.internal("data/MainTheme.mp3"))
    val gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/GameOver.wav"))

    init {
        batcher.projectionMatrix = cam.combined
        cam.setToOrtho(false, screenWidth, screenHeight);
        mainThemeSound.play()
        createNewEnemies()
    }

    fun onStart() {

    }

    fun render(delta: Float) {
        batcher.projectionMatrix = cam.combined
        batcher.begin()
        background.updateAndRender(delta, batcher)
        renderEnemies(delta, batcher)
        mainGamePlace.render(batcher)
        batcher.end()
        if (mainGamePlace.isGameOver()) {
            mainThemeSound.stop()
            gameOverSound.play()
            game.screen = GameOverScreen(game)
        }
    }

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
        checkForCollision(delta, rknEnemiesToRemove)
        clearEnemies(rknEnemiesToRemove)
    }

    private fun createNewEnemies() {
        val horizontalBounds = (screenWidth - GlobalConfig.BIG_ENEMY_WIDTH).toInt()
        val verticalBounds = (screenHeight / 2).toInt()
        if (bigEnemies.size < GlobalConfig.MAX_ENEMY_COUNT) {
            var countToAdd = GlobalConfig.MAX_ENEMY_COUNT - bigEnemies.size
            while (countToAdd > 0) {
                bigEnemies.add(BigEnemy(random.nextInt(horizontalBounds).toFloat(), screenHeight + random.nextInt(verticalBounds).toFloat()))
                countToAdd--
            }
        }
    }

    private fun checkForCollision(delta: Float, rknEnemiesToRemove: MutableList<BigEnemy>) {
        for (bigEnemy in bigEnemies) {
            if (bigEnemy.collisionRect.collidesWith(mainGamePlace.telegramPlayer.playerRect)) {
                mainGamePlace.telegramPlayer.hit()
            }
        }
    }

    private fun clearEnemies(enemies: MutableList<BigEnemy>) {
        for (enemy in enemies) {
            bigEnemies.remove(enemy)
        }
    }
}
