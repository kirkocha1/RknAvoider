package com.rknavoider.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rknavoider.RknAvoiderGame

class SplashScreen(private val avoiderGame: RknAvoiderGame) : Screen {

    private val screenWidth = Gdx.graphics.width.toFloat()
    private val screenHeight = Gdx.graphics.height.toFloat()
    private val camera = OrthographicCamera(screenWidth, screenHeight).apply {
        setToOrtho(false, screenWidth, screenHeight)
    }

    private val batch = SpriteBatch().apply {
        projectionMatrix = camera.combined
    }

    private val bkg = Texture("bkg_splash.png")

    override fun render(delta: Float) {
        batch.projectionMatrix = camera.combined
        batch.begin()
        batch.draw(bkg, 0f, 0f, screenWidth, screenHeight)
        if (Gdx.input.isTouched) {
            avoiderGame.screen = MainScreen(avoiderGame)
        }
        batch.end()
    }

    override fun hide() {}

    override fun show() {}

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {}
}