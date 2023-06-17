package com.example.todoapp

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ToDoItemBinding

class ToDoItemAdapter : RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>() {

    var items: List<TodoItem> = ToDoItemRepository.getInstance().getItems()
        set(newValue) {
            field = newValue
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ToDoItemBinding.inflate(inflater, parent, false)
        return ToDoItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            task.text = item.text
            if (dateText.text != null) dateText.text = item.deadlineDate.toString()
            else dateText.visibility = View.GONE
            checkbox.isChecked = item.flag == true

            when (item.priority) {
                0 -> {
                    priority.visibility = View.VISIBLE
                    priority.setImageResource(R.drawable.low_priority)
                }

                2 -> {
                    priority.setImageResource(R.drawable.high_priority)
                    priority.visibility = View.VISIBLE
                }

                else -> {
                    priority.visibility = View.GONE
                }
            }
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    class ToDoItemViewHolder(
        val binding: ToDoItemBinding
    ) : RecyclerView.ViewHolder(binding.root)

}