package com.rknavoider.models

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch

class ScrollingBackground(
        private val screenWidth: Float,
        private val screenHeight: Float
) {

    companion object {
        private val DEFAULT_SPEED = 200
        private val ACCELERATION = 50
        private val GOAL_REACH_ACCELERATION = 200
    }

    private var image: Texture
    private var y1: Float = 0.toFloat()
    private var y2: Float = 0.toFloat()
    private var speed: Int = 0//In pixels / second
    private var goalSpeed: Int = 0
    private var imageScale: Float = 0.toFloat()
    private var speedFixed: Boolean = false

    init {
        image = Texture("bkg_1.png")
        y1 = 0f
        y2 = image.height.toFloat()
        speed = 0
        goalSpeed = DEFAULT_SPEED
        imageScale = screenWidth / image.width
        speedFixed = true
    }

    fun updateAndRender(deltaTime: Float, batch: SpriteBatch) {
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += (GOAL_REACH_ACCELERATION * deltaTime).toInt()
            if (speed > goalSpeed)
                speed = goalSpeed
        } else if (speed > goalSpeed) {
            speed -= (GOAL_REACH_ACCELERATION * deltaTime).toInt()
            if (speed < goalSpeed)
                speed = goalSpeed
        }

        if (!speedFixed)
            speed += (ACCELERATION * deltaTime).toInt()

        y1 -= speed * deltaTime
        y2 -= speed * deltaTime

        //if image reached the bottom of screen and is not visible, put it back on top
        if (y1 + image.height * imageScale <= 0)
            y1 = y2 + image.height * imageScale

        if (y2 + image.height * imageScale <= 0)
            y2 = y1 + image.height * imageScale

        //Render
        batch.draw(image, 0f, y1, screenWidth, image.height * imageScale)
        batch.draw(image, 0f, y2, screenWidth, image.height * imageScale)
    }

    fun setSpeed(goalSpeed: Int) {
        this.goalSpeed = goalSpeed
    }

    fun setSpeedFixed(speedFixed: Boolean) {
        this.speedFixed = speedFixed
    }

}