package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.SpriteBatch


class MainGameRenderer(private val mainGamePlace: MainGamePlace) {

    private var cam = OrthographicCamera(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val batcher = SpriteBatch()

    init {
        batcher.projectionMatrix = cam.combined
    }

    fun render(delta: Float) {
        batcher.begin()
        mainGamePlace.render(batcher)
        batcher.end()
    }
}
