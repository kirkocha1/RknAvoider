package com.rknavoider.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rknavoider.configs.GlobalConfig
import com.rknavoider.utils.CollisionRect
import com.rknavoider.utils.Drawer

class Bullet(var x: Float, var y: Float) : Drawer {
    val SPEED = 700
    private var texture = Texture("BulletReady.png")
    var collisionRect = CollisionRect(x, y, GlobalConfig.BULLET_WIDTH, GlobalConfig.BULLET_WIDTH)
    var remove = false

    fun update(deltaTime: Float) {
        y += SPEED * deltaTime
        if (y < -GlobalConfig.BULLET_WIDTH) {
            remove = true
        }
        collisionRect.move(x, y)
    }

    override fun draw(batcher: SpriteBatch) {
        batcher.draw(texture, x, y, GlobalConfig.BULLET_WIDTH.toFloat(), GlobalConfig.BULLET_WIDTH.toFloat())
    }

    override fun dispose() {
        texture.dispose()
    }
}