package com.denofdevelopers.todolist.data

import com.denofdevelopers.todolist.R
import com.denofdevelopers.todolist.model.TodoItem

object DataSource {
    val todos = listOf(
        TodoItem(
            title = R.string.study,
            type = R.string.learning,
            description = R.string.study_description
        ),
        TodoItem(
            title = R.string.work_out,
            type = R.string.personal,
            description = R.string.work_out_description
        ),
        TodoItem(
            title = R.string.pay_bills,
            type = R.string.personal,
            description = R.string.pay_bills_description
        ),
        TodoItem(
            title = R.string.buy_groceries,
            type = R.string.personal,
            description = R.string.buy_groceries_description
        ),
        TodoItem(
            title = R.string.finish_project,
            type = R.string.work,
            description = R.string.finish_project_description
        )
    )
}