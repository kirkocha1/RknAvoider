package com.telegramvsrkn.models

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.telegramvsrkn.ScrollingBackground


class MainGameRenderer(
        private val mainGamePlace: MainGamePlace,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    var cam = OrthographicCamera(screenWidth, screenHeight)
    private val batcher = SpriteBatch()
    private val background = ScrollingBackground(screenWidth, screenHeight)

    init {
        batcher.projectionMatrix = cam.combined
        cam.setToOrtho(false, screenWidth, screenHeight);
    }

    fun render(delta: Float) {
        batcher.projectionMatrix = cam.combined
        batcher.begin()
        background.updateAndRender(delta, batcher)
        mainGamePlace.render(batcher)
        batcher.end()
    }
}
