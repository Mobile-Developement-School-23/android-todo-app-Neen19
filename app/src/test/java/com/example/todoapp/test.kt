package com.example.todoapp

import android.icu.util.Calendar

fun main() {
    val rep = ToDoItemRepository()
    for (i in rep.getItems()) println(i)
}