package com.rknavoider

import com.badlogic.gdx.Game
import com.rknavoider.screens.SplashScreen

class RknAvoiderGame : Game() {

    override fun create() = setScreen(SplashScreen(this))
}
