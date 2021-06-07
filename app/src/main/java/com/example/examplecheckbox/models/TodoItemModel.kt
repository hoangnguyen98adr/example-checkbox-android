package com.example.examplecheckbox.models

data class TodoItemModel(
    val id: Int,
    val name: String,
    var isChecked: Boolean = false
)