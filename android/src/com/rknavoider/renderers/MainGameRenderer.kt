package com.rknavoider.renderers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.rknavoider.RknAvoiderGame
import com.rknavoider.models.RknAvoiderWorld
import com.rknavoider.models.ScrollingBackground
import com.rknavoider.screens.GameOverScreen


class MainGameRenderer(
        private val avoiderGame: RknAvoiderGame,
        private val rknAvoiderPlace: RknAvoiderWorld,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    val cam = OrthographicCamera(screenWidth, screenHeight).apply {
        setToOrtho(false, screenWidth, screenHeight);
    }
    private val batcher = SpriteBatch().apply {
        projectionMatrix = cam.combined
    }
    private val background = ScrollingBackground(screenWidth, screenHeight)
    private val defaultFont = BitmapFont()
    private val gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/GameOver.wav"))

    fun render(delta: Float) {
        batcher.begin()
        background.updateAndRender(delta, batcher)
        rknAvoiderPlace.render(delta, batcher)
        val scoreLabel = Label("SCORE: " + rknAvoiderPlace.score, Label.LabelStyle(defaultFont, Color.WHITE));
        val lifeCount = Label("LIFE: " + rknAvoiderPlace.lifesCount, Label.LabelStyle(defaultFont, Color.WHITE));
        scoreLabel.fontScaleX = 3f
        scoreLabel.fontScaleY = 3f
        lifeCount.fontScaleX = 3f
        lifeCount.fontScaleY = 3f
        lifeCount.setPosition(screenWidth / 2 - lifeCount.width / 2, screenHeight - lifeCount.height - 70)
        scoreLabel.setPosition(screenWidth / 2 - scoreLabel.width / 2, screenHeight - scoreLabel.height - 30)
        lifeCount.draw(batcher, 1f)
        scoreLabel.draw(batcher, 1f)
        batcher.end()
        if (rknAvoiderPlace.isGameOver()) {
            gameOverSound.play()
            avoiderGame.screen = GameOverScreen(avoiderGame)
        }
    }
}
