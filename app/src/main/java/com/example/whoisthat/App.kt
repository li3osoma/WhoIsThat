package com.example.whoisthat

import android.app.Application
import com.example.whoisthat.model.Options
import com.example.whoisthat.service.CharacterService

class App : Application() {
    val characterService= CharacterService(Options.DEFAULT)
}