package com.denofdevelopers.todolist.data

import com.denofdevelopers.todolist.model.TodoItem
import java.util.concurrent.TimeUnit

interface TodoRepository {
    fun scheduleReminder(duration: Long, unit: TimeUnit, todoTitle: String)
    val todos: List<TodoItem>
}