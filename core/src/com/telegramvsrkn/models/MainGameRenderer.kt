package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class MainGameRenderer(
        private val mainGamePlace: MainGamePlace,
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    var cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val batcher = SpriteBatch()

    init {
        batcher.projectionMatrix = cam.combined
        cam.setToOrtho(true, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat());
    }

    fun render(delta: Float) {
        batcher.projectionMatrix = cam.combined
        batcher.begin()
        mainGamePlace.render(batcher)
        batcher.end()
    }
}
