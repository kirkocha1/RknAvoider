package com.rknavoider.renderers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.utils.Align
import com.rknavoider.models.ScrollingBackground
import com.rknavoider.TelegramVsRknGame
import com.rknavoider.models.MainGamePlace
import com.rknavoider.screens.GameOverScreen


class MainGameRenderer(
        private val game: TelegramVsRknGame,
        private val mainGamePlace: MainGamePlace,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    var cam = OrthographicCamera(screenWidth, screenHeight)
    private val batcher = SpriteBatch()
    private val background = ScrollingBackground(screenWidth, screenHeight)
    private val defaultFont = BitmapFont()
    private val scoreLayout = GlyphLayout(defaultFont, "SCORE: " + mainGamePlace.score, Color.WHITE, 20f, Align.left, false)
    private val lifeLayout = GlyphLayout(defaultFont, "LIFE: " + mainGamePlace.score, Color.WHITE, 20f, Align.left, false)
    val gameOverSound = Gdx.audio.newSound(Gdx.files.internal("data/GameOver.wav"))

    val fontBatch = SpriteBatch()

    init {
        batcher.projectionMatrix = cam.combined
        cam.setToOrtho(false, screenWidth, screenHeight);
    }

    fun render(delta: Float) {
        batcher.projectionMatrix = cam.combined
        batcher.begin()
        background.updateAndRender(delta, batcher)
        mainGamePlace.render(delta, batcher)
        scoreLayout.setText(defaultFont, mainGamePlace.score.toString())
        scoreLayout.width = scoreLayout.width * 10
        scoreLayout.height = scoreLayout.height * 10
        val scoreLabel = Label("SCORE: " + mainGamePlace.score, Label.LabelStyle(defaultFont, Color.WHITE));
        val lifeCount = Label("LIFE: " + mainGamePlace.telegramPlayer.lifeCount, Label.LabelStyle(defaultFont, Color.WHITE));
        scoreLabel.fontScaleX = 3f
        scoreLabel.fontScaleY = 3f
        lifeCount.fontScaleX = 3f
        lifeCount.fontScaleY = 3f
        lifeCount.setPosition(screenWidth / 2 - scoreLayout.width / 2, screenHeight - scoreLayout.height - 70)
        scoreLabel.setPosition(screenWidth / 2 - scoreLayout.width / 2, screenHeight - scoreLayout.height - 30)
        lifeCount.draw(batcher, 1f)
        scoreLabel.draw(batcher, 1f)
        batcher.end()

        if (mainGamePlace.isGameOver()) {
            gameOverSound.play()
            game.screen = GameOverScreen(game)
        }
    }
}
