package com.telegramvsrkn.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.telegramvsrkn.GlobalConfig
import com.telegramvsrkn.TelegramVsRknGame

class GameOverScreen(private val game: TelegramVsRknGame) : Screen {

    val screenWidth = Gdx.graphics.width.toFloat()
    val screenHeight = Gdx.graphics.height.toFloat()
    val batch = SpriteBatch()
    val bkg = Texture("bkg_game_over.png")
    var timeToShow: Float = GlobalConfig.GAME_OVER_TIME
    var cam = OrthographicCamera(screenWidth, screenHeight)
    var imageScale = screenWidth / bkg.width

    init {
        batch.projectionMatrix = cam.combined
        cam.setToOrtho(false, screenWidth, screenHeight);
    }

    override fun hide() {

    }

    override fun show() {

    }

    override fun render(delta: Float) {
        batch.projectionMatrix = cam.combined
        batch.begin()
        batch.draw(bkg, 0f, 0f, screenWidth, screenHeight)
        batch.end()
        timeToShow -= delta
        if (timeToShow < 0) {
            game.screen = MainScreen(game)
        }
    }

    override fun pause() {
    }

    override fun resume() {

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun dispose() {

    }
}