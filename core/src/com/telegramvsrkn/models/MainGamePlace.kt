package com.telegramvsrkn.models

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class MainGamePlace(var screnWidth: Float, var screenHeight: Float) {

    var telegramPlayer = TelegramPlayer(screnWidth / 2, screenHeight / 2, 150, 150)

    fun update(delta: Float) {
        Gdx.app.log("GameWorld", "update");
        telegramPlayer.update(delta)
    }

    fun render(batch: SpriteBatch) {
        telegramPlayer.draw(batch)
    }
}
