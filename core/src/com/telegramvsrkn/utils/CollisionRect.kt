package com.telegramvsrkn.utils

class CollisionRect(var x: Float, var y: Float, var width: Int, var height: Int) {

    fun move(x: Float, y: Float) {
        this.x = x
        this.y = y
    }

    fun collidesWith(rect: CollisionRect): Boolean {
        return x < rect.x + rect.width && y < rect.y + rect.height && x + width > rect.x && y + height > rect.y
    }

}