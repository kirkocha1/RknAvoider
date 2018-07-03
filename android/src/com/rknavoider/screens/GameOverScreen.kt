package com.rknavoider.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rknavoider.RknAvoiderGame
import com.rknavoider.configs.GlobalConfig

class GameOverScreen(private val avoiderGame: RknAvoiderGame) : Screen {

    private val screenWidth = Gdx.graphics.width.toFloat()
    private val screenHeight = Gdx.graphics.height.toFloat()
    private var camera = OrthographicCamera(screenWidth, screenHeight).apply {
        setToOrtho(false, screenWidth, screenHeight);
    }
    private val batch = SpriteBatch().apply {
        projectionMatrix = camera.combined
    }
    private val bkg = Texture("bkg_game_over.png")
    private var timeToShow: Float = GlobalConfig.GAME_OVER_TIME

    override fun render(delta: Float) {
        batch.begin()
        batch.draw(bkg, 0f, 0f, screenWidth, screenHeight)
        batch.end()
        timeToShow -= delta
        if (timeToShow < 0) {
            avoiderGame.screen = MainScreen(avoiderGame)
        }
    }

    override fun hide() {}

    override fun show() {}

    override fun pause() {}

    override fun resume() {}

    override fun resize(width: Int, height: Int) {}

    override fun dispose() {
        bkg.dispose()
        batch.dispose()
    }
}