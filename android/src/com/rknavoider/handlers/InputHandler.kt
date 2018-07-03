package com.rknavoider.handlers

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.rknavoider.utils.ClickListener

class InputHandler(
        private val clickListener: ClickListener,
        private val camera: OrthographicCamera
) : InputProcessor {
    override fun touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int) = false

    override fun mouseMoved(screenX: Int, screenY: Int) = false

    override fun keyTyped(character: Char) = false

    override fun scrolled(amount: Int) = false

    override fun keyUp(keycode: Int) = false

    override fun touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean {
        clickListener.onClick(getInputInGameWorld().x, getInputInGameWorld().y)
        return true
    }

    override fun keyDown(keycode: Int) = false

    override fun touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int) = false

    fun getInputInGameWorld(): Vector2 {
        val inputScreen = Vector3(Gdx.input.x.toFloat(), Gdx.input.y.toFloat(), 0f)
        val projected = camera.project(inputScreen)
        return Vector2(projected.x, projected.y)
    }
}