package com.denofdevelopers.todolist.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.denofdevelopers.todolist.FIVE_SECONDS
import com.denofdevelopers.todolist.ONE_DAY
import com.denofdevelopers.todolist.ONE_MINUTE
import com.denofdevelopers.todolist.R
import com.denofdevelopers.todolist.SEVEN_DAYS
import com.denofdevelopers.todolist.THIRTY_DAYS
import com.denofdevelopers.todolist.THIRTY_SECONDS
import com.denofdevelopers.todolist.TWO_MINUTES
import com.denofdevelopers.todolist.data.DataSource
import com.denofdevelopers.todolist.data.Reminder
import com.denofdevelopers.todolist.model.TodoItem
import com.denofdevelopers.todolist.ui.theme.ToDoListTheme
import java.util.concurrent.TimeUnit

@Composable
fun TodoListApp(todoListViewModel: TodoListViewModel = viewModel(factory = TodoListViewModel.Factory)) {
    val layoutDirection = LocalLayoutDirection.current
    ToDoListTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(
                    start = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateStartPadding(layoutDirection),
                    end = WindowInsets.safeDrawing
                        .asPaddingValues()
                        .calculateEndPadding(layoutDirection)
                ),
        ) {
            TodoList(
                todos = todoListViewModel.todos,
                onScheduleReminder = { todoListViewModel.scheduleReminder(it) }
            )
        }
    }
}

@Composable
fun TodoList(
    todos: List<TodoItem>,
    onScheduleReminder: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedTodo by rememberSaveable { mutableStateOf(todos[0]) }
    var showReminderDialog by rememberSaveable { mutableStateOf(false) }
    LazyColumn(
        contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_medium)),
        modifier = modifier
    ) {
        items(items = todos) {
            TodoListItem(
                todoItem = it,
                onItemSelect = { plant ->
                    selectedTodo = plant
                    showReminderDialog = true
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
    if (showReminderDialog) {
        ReminderDialogContent(
            onDialogDismiss = { showReminderDialog = false },
            todoTitle = stringResource(selectedTodo.title),
            onScheduleReminder = onScheduleReminder
        )
    }
}

@Composable
fun TodoListItem(
    todoItem: TodoItem,
    onItemSelect: (TodoItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier
        .clickable { onItemSelect(todoItem) }
    ) {
        Column(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_medium))
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = stringResource(todoItem.title),
                modifier = Modifier.fillMaxWidth(),
                style = typography.headlineSmall,
                textAlign = TextAlign.Center
            )
            Text(text = stringResource(todoItem.type), style = typography.titleMedium)
            Text(text = stringResource(todoItem.description), style = typography.titleMedium)
        }
    }
}

@Composable
fun ReminderDialogContent(
    onDialogDismiss: () -> Unit,
    todoTitle: String,
    onScheduleReminder: (Reminder) -> Unit,
    modifier: Modifier = Modifier
) {
    val reminders = listOf(
        Reminder(R.string.five_seconds, FIVE_SECONDS, TimeUnit.SECONDS, todoTitle),
        Reminder(R.string.thirty_seconds, THIRTY_SECONDS, TimeUnit.SECONDS, todoTitle),
        Reminder(R.string.one_minute, ONE_MINUTE, TimeUnit.MINUTES, todoTitle),
        Reminder(R.string.two_minutes, TWO_MINUTES, TimeUnit.MINUTES, todoTitle),
        Reminder(R.string.one_day, ONE_DAY, TimeUnit.DAYS, todoTitle),
        Reminder(R.string.one_week, SEVEN_DAYS, TimeUnit.DAYS, todoTitle),
        Reminder(R.string.one_month, THIRTY_DAYS, TimeUnit.DAYS, todoTitle)
    )

    AlertDialog(
        onDismissRequest = { onDialogDismiss() },
        confirmButton = {},
        title = { Text(stringResource(R.string.remind_me, todoTitle)) },
        text = {
            Column {
                reminders.forEach {
                    Text(
                        text = stringResource(it.durationRes),
                        modifier = Modifier
                            .clickable {
                                onScheduleReminder(it)
                                onDialogDismiss()
                            }
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
            }
        },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun PlantListItemPreview() {
    ToDoListTheme {
        TodoListItem(DataSource.todos[0], {})
    }
}

@Preview(showBackground = true)
@Composable
fun PlantListContentPreview() {
    TodoList(todos = DataSource.todos, onScheduleReminder = {})
}
