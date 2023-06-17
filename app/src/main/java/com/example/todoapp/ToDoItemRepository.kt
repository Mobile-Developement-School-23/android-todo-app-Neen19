package com.example.todoapp

import android.content.ContentValues.TAG
import android.icu.util.Calendar
import android.util.Log
import org.w3c.dom.Text
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


fun LocalDate.toStringDate(): String =
    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(this)


class ToDoItemRepository {


    private val items = mutableListOf<TodoItem>()


    init {
        for (i in 0..40) {
            items.add(
                TodoItem(
                    i.toString(),
                    "Some to do text $i",
                    i % 3,
                    (i % 2 == 0),
                    LocalDate.now().toStringDate(),
                )
            )
        }
    }

    fun deleteItem(item: TodoItem) {
        items.remove(item)
    }

    fun getItems(): List<TodoItem> {
        return items
    }


    fun getDoneCount(): Int {
        var count: Int = 0
        for (i in items) {
            if (i.flag == true) count++
        }
        return count
    }

    fun addItem(id: String, text: String, priority: Int, deadlineDate: String?) {
        items.add(
            TodoItem(
                id,
                text,
                priority,
                false,
                LocalDate.now().toStringDate(),
                null,
                deadlineDate
            )
        )
    }

    companion object {
        private var instance: ToDoItemRepository? = null

        fun getInstance(): ToDoItemRepository {
            if (instance == null) {
                instance = ToDoItemRepository()
            }
            return instance!!
        }
    }

}