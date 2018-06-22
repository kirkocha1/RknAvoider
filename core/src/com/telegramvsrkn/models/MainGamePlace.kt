package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainGamePlace(private val screnWidth: Float, private val screenHeight: Float) {

    val telegramPlayer = TelegramPlayer(screnWidth / 2, screenHeight / 2, 150, 150)


    fun update(delta: Float) {
        Gdx.app.log("GameWorld", "update");
        telegramPlayer.update(delta)
    }

    fun render(batch: SpriteBatch) {
        telegramPlayer.draw(batch)
    }

}
