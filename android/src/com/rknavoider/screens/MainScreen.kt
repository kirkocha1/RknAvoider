package com.rknavoider.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.GL20
import com.rknavoider.TelegramVsRknGame
import com.rknavoider.handlers.InputHandler
import com.rknavoider.models.MainGamePlace
import com.rknavoider.renderers.MainGameRenderer

class MainScreen(game: TelegramVsRknGame) : Screen {
    val screenWidth = Gdx.graphics.width.toFloat()
    val screenHeight = Gdx.graphics.height.toFloat()
    private val renderer: MainGameRenderer
    private val mainGamePlace: MainGamePlace
    val mainThemeSound = Gdx.audio.newSound(Gdx.files.internal("data/MainTheme.mp3"))


    init {
        mainGamePlace = MainGamePlace(screenWidth, screenHeight)
        renderer = MainGameRenderer(game, mainGamePlace, screenWidth, screenHeight)
        Gdx.input.inputProcessor = InputHandler(mainGamePlace.telegramPlayer, renderer.cam)
    }

    override fun show() {
        mainThemeSound.loop()
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
