package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.telegramvsrkn.ScrollingBackground
import com.telegramvsrkn.TelegramVsRknGame
import com.telegramvsrkn.screens.GameOverScreen


class MainGameRenderer(
        private val game: TelegramVsRknGame,
        private val mainGamePlace: MainGamePlace,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    var cam = OrthographicCamera(screenWidth, screenHeight)
    private val batcher = SpriteBatch()
    private val background = ScrollingBackground(screenWidth, screenHeight)

    val gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/GameOver.wav"))

    init {
        batcher.projectionMatrix = cam.combined
        cam.setToOrtho(false, screenWidth, screenHeight);
    }

    fun render(delta: Float) {
        batcher.projectionMatrix = cam.combined
        batcher.begin()
        background.updateAndRender(delta, batcher)
        mainGamePlace.render(delta, batcher)
        batcher.end()
        if (mainGamePlace.isGameOver()) {
//            (game.screen as MainScreen).mainThemeSound.pause()
            gameOverSound.play()
            game.screen = GameOverScreen(game)
        }
    }
}
