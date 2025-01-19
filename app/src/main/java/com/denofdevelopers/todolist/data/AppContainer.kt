package com.denofdevelopers.todolist.data

import android.content.Context

interface AppContainer {
    val todoRepository: TodoRepository
}

class DefaultAppContainer(context: Context) : AppContainer {
    override val todoRepository = WorkManagerTodoRepository(context)
}