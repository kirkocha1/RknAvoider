package com.telegramvsrkn.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.telegramvsrkn.GlobalConfig
import com.telegramvsrkn.utils.CollisionRect

class BigEnemy(var x: Float, var y: Float) : Drawer {

    private val texture = Texture("ic_enemy.png")
    val collisionRect = CollisionRect(x, y, GlobalConfig.BIG_ENEMY_WIDTH, GlobalConfig.BIG_ENEMY_WIDTH)
    val SPEED = 250
    var remove = false
    fun update(deltaTime: Float) {
        y -= SPEED * deltaTime
        if (y < -GlobalConfig.BIG_ENEMY_WIDTH) {
            remove = true
        }
        collisionRect.move(x, y)
    }

    override fun draw(batcher: SpriteBatch) {
        batcher.draw(texture, x, y, GlobalConfig.BIG_ENEMY_WIDTH.toFloat(), GlobalConfig.BIG_ENEMY_WIDTH.toFloat())
    }

}