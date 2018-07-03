package com.rknavoider.utils

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface Drawer {
    fun draw(batcher: SpriteBatch)
    fun dispose()
}