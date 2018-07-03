package com.rknavoider.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.rknavoider.configs.GlobalConfig
import com.rknavoider.utils.CollisionRect
import com.rknavoider.utils.Drawer
import java.util.*

class BigRknEnemy(var x: Float, var y: Float) : Drawer {
    var random = Random()
    private val texture = Texture("ic_enemy_cut.png")
    val collisionRect = CollisionRect(x, y, GlobalConfig.BIG_ENEMY_WIDTH, GlobalConfig.BIG_ENEMY_HEIGHT)
    val SPEED = random.nextFloat() * (GlobalConfig.MAX_ENEMY_SPEED)
    var remove = false

    fun update(deltaTime: Float) {
        y -= SPEED * deltaTime
        if (y < -GlobalConfig.BIG_ENEMY_HEIGHT) {
            remove = true
        }
        collisionRect.move(x, y)
    }

    override fun draw(batcher: SpriteBatch) {
        batcher.draw(texture, x, y, GlobalConfig.BIG_ENEMY_WIDTH.toFloat(), GlobalConfig.BIG_ENEMY_HEIGHT.toFloat())
    }

    override fun dispose() {
        texture.dispose()
    }

}