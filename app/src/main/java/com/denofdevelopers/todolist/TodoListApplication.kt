package com.denofdevelopers.todolist

import android.app.Application
import com.denofdevelopers.todolist.data.AppContainer
import com.denofdevelopers.todolist.data.DefaultAppContainer

class TodoListApplication: Application() {

    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}
