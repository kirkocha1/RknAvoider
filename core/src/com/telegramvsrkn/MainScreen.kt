package com.telegramvsrkn

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.telegramvsrkn.handlers.InputHandler
import com.telegramvsrkn.models.MainGamePlace
import com.telegramvsrkn.models.MainGameRenderer

class MainScreen : Screen {
    val screenWidth = Gdx.graphics.width.toFloat()
    val screenHeight = Gdx.graphics.height.toFloat()
    private val mainGamePlace = MainGamePlace(screenWidth, screenHeight)
    private val renderer = MainGameRenderer(mainGamePlace)

    init {
        Gdx.input.inputProcessor = InputHandler(mainGamePlace.telegramPlayer)
    }

    override fun show() {

    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        mainGamePlace.update(delta)
        renderer.render(delta)

    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {

    }
}
