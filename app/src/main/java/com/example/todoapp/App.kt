package com.example.todoapp

import android.app.Application

class App : Application() {
    val rep = ToDoItemRepository()
}