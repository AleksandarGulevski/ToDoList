package com.denofdevelopers.todolist.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.denofdevelopers.todolist.TodoListApplication
import com.denofdevelopers.todolist.data.Reminder
import com.denofdevelopers.todolist.data.TodoRepository

class TodoListViewModel (private val todoRepository: TodoRepository) : ViewModel() {

    internal val todos = todoRepository.todos

    fun scheduleReminder(reminder: Reminder) {
        todoRepository.scheduleReminder(reminder.duration, reminder.unit, reminder.todoTitle)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val todoRepository =
                    (this[APPLICATION_KEY] as TodoListApplication).container.todoRepository
                TodoListViewModel(
                    todoRepository = todoRepository
                )
            }
        }
    }
}
