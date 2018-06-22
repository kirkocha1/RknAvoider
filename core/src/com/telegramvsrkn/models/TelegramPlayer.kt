package com.telegramvsrkn.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2


class TelegramPlayer(var x: Float, var y: Float, var width: Int, var height: Int) : Drawer {
    private val position: Vector2 = Vector2(x / 2, y / 2);
    private val previousClickPosition = Vector2(0f, 0f)
    private val telegramTexture = Texture("ic_telegram_player.png")
    fun update(delta: Float) {

    }

    fun onClick(screenX: Int, screenY: Int) {
        if (previousClickPosition.x > screenX) {
            position.x -= 4
        } else {
            position.x += 4
        }
        if (previousClickPosition.y > screenY) {
            position.y += 4
        } else {
            position.y -= 4
        }
        previousClickPosition.x = screenX.toFloat()
        previousClickPosition.y = screenY.toFloat()
    }

    override fun draw(batcher: SpriteBatch) {
        batcher.draw(telegramTexture, position.x, position.y, telegramTexture.width.toFloat(), telegramTexture.height.toFloat())
    }
}

interface Drawer {
    fun draw(batcher: SpriteBatch)
}