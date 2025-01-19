package com.denofdevelopers.todolist.data

import android.content.Context
import androidx.work.WorkManager
import com.denofdevelopers.todolist.model.TodoItem
import java.util.concurrent.TimeUnit

class WorkManagerTodoRepository(context: Context) : TodoRepository {
    private val workManager = WorkManager.getInstance(context)

    override val todos: List<TodoItem>
        get() = DataSource.todos

    override fun scheduleReminder(duration: Long, unit: TimeUnit, todoTitle: String) {}
}