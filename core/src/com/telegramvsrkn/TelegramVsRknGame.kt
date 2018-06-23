package com.telegramvsrkn

import com.badlogic.gdx.Game
import com.telegramvsrkn.screens.SplashScreen

class TelegramVsRknGame : Game() {


    override fun create() {

        setScreen(SplashScreen(this))
    }

    override fun render() {
        super.render()

    }
}
