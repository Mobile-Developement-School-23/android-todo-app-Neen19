package com.example.todoapp


import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


fun LocalDate.toStringDate(): String =
    DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(this)


enum class Priority {
    HIGH_PRIORITY,
    NO_PRIORITY,
    LOW_PRIORITY,
}

class ToDoItemRepository {


    private val items = mutableListOf<TodoItem>()


    init {
        for (i in 0..40) {
            items.add(
                TodoItem(
                    i.toString(),
                    "Some to do text $i",
                    when (i % 3) {
                        0 -> Priority.LOW_PRIORITY
                        1 -> Priority.NO_PRIORITY
                        else -> Priority.HIGH_PRIORITY
                    },
                    (i % 2 == 0),
                    LocalDate.now().toStringDate(),
                    deadlineDate = if (i % 2 == 0) LocalDate.now().toStringDate() else null
                )
            )
        }
    }


//    fun deleteItem(position: Int) {
//        if (position != -1) items.removeAt(position)
//    }


    fun getItems(): List<TodoItem> {
        return items
    }


    fun getDoneCount(): Int {
        var count = 0
        for (i in items) {
            if (i.flag) count++
        }
        return count
    }

    fun addItem(id: String, text: String, priority: Priority, deadlineDate: String?) {
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

    fun editItem(position: Int, text: String, priority: Priority, deadlineDate: String?) {
        with(items[position]) {
            this.text = text
            this.priority = priority
            this.deadlineDate = deadlineDate
        }
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