package com.example.todoapp

import java.time.LocalDate
import java.util.Date

data class TodoItem(
    val id: String,
    val text: String,
    val priority: Int,
    var flag: Boolean,
    val createDate: String,
    var changeDate: String? = null,
    var deadlineDate: String? = null
)