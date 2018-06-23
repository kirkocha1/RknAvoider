package com.telegramvsrkn

import com.badlogic.gdx.Game
import com.telegramvsrkn.screens.MainScreen

class TelegramVsRknGame : Game() {


    override fun create() {

        setScreen(MainScreen(this))
    }

    override fun render() {
        super.render()

    }
}
