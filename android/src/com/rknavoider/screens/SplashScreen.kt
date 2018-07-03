package com.rknavoider.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rknavoider.TelegramVsRknGame

class SplashScreen(private val game: TelegramVsRknGame) : Screen {
    val screenWidth = Gdx.graphics.width.toFloat()
    val screenHeight = Gdx.graphics.height.toFloat()
    val batch = SpriteBatch()
    val bkg = Texture("bkg_splash.png")
    var cam = OrthographicCamera(screenWidth, screenHeight)

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
        if (Gdx.input.isTouched) {
            game.screen = MainScreen(game)
        }
        batch.end()
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