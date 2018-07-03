package com.rknavoider.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.rknavoider.RknAvoiderGame
import com.rknavoider.handlers.InputHandler
import com.rknavoider.models.RknAvoiderWorld
import com.rknavoider.renderers.MainGameRenderer

class MainScreen(avoiderGame: RknAvoiderGame) : Screen {
    private val screenWidth = Gdx.graphics.width.toFloat()
    private val screenHeight = Gdx.graphics.height.toFloat()
    private val rknAvoiderWorld = RknAvoiderWorld(screenWidth, screenHeight)
    private val renderer = MainGameRenderer(avoiderGame, rknAvoiderWorld, screenWidth, screenHeight)
    private val mainThemeSound = Gdx.audio.newSound(Gdx.files.internal("data/MainTheme.mp3"))

    init {
        Gdx.input.inputProcessor = InputHandler(rknAvoiderWorld, renderer.cam)
    }

    override fun show() {
        mainThemeSound.loop()
    }

    override fun render(delta: Float) {
        Gdx.gl.glClearColor(1f, 1f, 1f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        rknAvoiderWorld.update(delta)
        renderer.render(delta)
    }

    override fun resize(width: Int, height: Int) {}

    override fun pause() {}

    override fun resume() {}

    override fun hide() {}

    override fun dispose() {
        mainThemeSound.dispose()
        rknAvoiderWorld.dispose()
    }
}
