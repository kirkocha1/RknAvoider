package com.telegramvsrkn.handlers

import com.badlogic.gdx.InputProcessor
import com.telegramvsrkn.models.TelegramPlayer

class InputHandler(private val telegramPlayer: TelegramPlayer) : InputProcessor {
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = false

    override fun mouseMoved(screenX: Int, screenY: Int) = false

    override fun keyTyped(character: Char) = false

    override fun scrolled(amount: Int) = false

    override fun keyUp(keycode: Int) = false

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        telegramPlayer.onClick(screenX, screenY)
        return true
    }

    override fun keyDown(keycode: Int) = false

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = false

}