package com.example.todoapp

data class TodoItem(
    val id: String,
    var text: String,
    var priority: Priority,
    var flag: Boolean,
    val createDate: String,
    var changeDate: String? = null,
    var deadlineDate: String? = null
)