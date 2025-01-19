package com.denofdevelopers.todolist.model

import android.os.Parcelable
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class TodoItem(
    @StringRes val title: Int,
    @StringRes val description: Int,
    @StringRes val type: Int,
): Parcelable