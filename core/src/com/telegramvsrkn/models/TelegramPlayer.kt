package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2


class TelegramPlayer(var x: Float, var y: Float, var width: Int, var height: Int) : Drawer {
    private val position: Vector2 = Vector2(x, y);
    private val previousClickPosition = Vector2(x, y)
    private val telegramTexture = Texture("ic_telegram_player.png")
    private val SPEED = 300f

    fun update(delta: Float) {

    }

    fun onClick(screenX: Float, screenY: Float) {
        if (previousClickPosition.x > screenX) {
            position.x -= SPEED * Gdx.graphics.getDeltaTime();
        }
        if (previousClickPosition.x < screenX) {
            position.x += SPEED * Gdx.graphics.getDeltaTime();
        }
        checkForHorizontalBounds()
        if (previousClickPosition.y > screenY) {
            position.y += SPEED * Gdx.graphics.getDeltaTime();
        }
        if (previousClickPosition.y < screenY) {
            position.y -= SPEED * Gdx.graphics.getDeltaTime();
        }
        checkForVerticalBounds()
        previousClickPosition.x = screenX
        previousClickPosition.y = screenY
    }

    private fun checkForHorizontalBounds() {
        if (position.x + width > Gdx.graphics.width) {
            position.x = Gdx.graphics.width.toFloat() - width
        }
        if (position.x < 0) {
            position.x = 0f
        }
    }

    private fun checkForVerticalBounds() {
        if (position.y + height > Gdx.graphics.height) {
            position.y = Gdx.graphics.height.toFloat() - height
        }
        if (position.y < 0) {
            position.y = 0f
        }
    }

    override fun draw(batcher: SpriteBatch) {
        batcher.draw(telegramTexture, position.x, position.y, width.toFloat(), height.toFloat())
    }


}

interface Drawer {
    fun draw(batcher: SpriteBatch)
}