package com.rknavoider.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.rknavoider.configs.GlobalConfig
import com.rknavoider.utils.CollisionRect
import com.rknavoider.utils.Drawer


class TelegramPlayer(
        private var x: Float,
        private var y: Float,
        private var width: Int,
        private var height: Int,
        var lifeCount: Int = GlobalConfig.LIFE_COUNT
) : Drawer {

    private val previousClickPosition = Vector2(x, y)
    private val telegramTexture = Texture("ic_telegram_player.png")
    private val telegramHurtedTexture = Texture("telegram_hurted.png")
    private val hitSound = Gdx.audio.newSound(Gdx.files.internal("data/Burst.mp3"))
    private val SPEED = 500f
    private var isImmortal = false
    private var isBlank = false
    private var immortalTimer = GlobalConfig.IMMORTAL_TIME

    val position: Vector2 = Vector2(x, y);
    var playerRect = CollisionRect(position.x, position.y, width, height)
    var wasKilled = false

    fun update(delta: Float) {
        immortalTimer -= delta
        if (immortalTimer < 0) {
            isImmortal = false
        }
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
        playerRect.move(position.x, position.y)
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
        if (!wasKilled) {
            if (isImmortal) {
                drawImmortal(batcher)
            } else {
                if (lifeCount <= 1) {
                    batcher.draw(telegramHurtedTexture, position.x, position.y, width.toFloat(), height.toFloat())
                } else {
                    batcher.draw(telegramTexture, position.x, position.y, width.toFloat(), height.toFloat())
                }

            }
        }
    }

    fun hit() {
        if (!isImmortal) {
            lifeCount--
            if (lifeCount < 0) {
                wasKilled = true
            } else {
                hitSound.play()
                startImmortalTimer()
            }
        }
    }

    private fun startImmortalTimer() {
        isImmortal = true
        immortalTimer = GlobalConfig.IMMORTAL_TIME
    }

    private fun drawImmortal(batcher: SpriteBatch) {
        if (isBlank) {
            batcher.draw(telegramTexture, position.x, position.y, width.toFloat(), height.toFloat())
            isBlank = false
        } else {
            isBlank = true
        }
    }
}

