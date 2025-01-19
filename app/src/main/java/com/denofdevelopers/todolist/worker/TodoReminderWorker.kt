package com.denofdevelopers.todolist.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.denofdevelopers.todolist.R

class TodoReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {

        val todoTitle = inputData.getString(titleKey)

        makeTodoReminderNotification(
            applicationContext.resources.getString(R.string.time_to_do, todoTitle),
            applicationContext
        )

        return Result.success()
    }

    companion object {
        const val titleKey = "TITLE"
    }
}