package com.example.recyclerviewrom

import android.app.Application
import com.example.recyclerviewrom.model.UsersService

class App:Application() {

    val usersService = UsersService()

}