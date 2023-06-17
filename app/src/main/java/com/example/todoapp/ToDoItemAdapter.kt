package com.example.todoapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ToDoItemBinding


interface itemTouchListener {

    fun onItemTouch(item: TodoItem) {}

    fun checkboxIsCheced(item: TodoItem ) {}

}


class ToDoItemAdapter(private val listener: itemTouchListener) :
    RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>(
    ), View.OnClickListener {


    override fun onClick(v: View?) {
        val item = v?.tag as TodoItem
        listener.onItemTouch(item)
    }

    var items: List<TodoItem> = ToDoItemRepository.getInstance().getItems()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ToDoItemBinding.inflate(inflater, parent, false)
        binding.infoIcon.setOnClickListener(this)
        binding.checkbox.isChecked()
        return ToDoItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            holder.itemView.tag = item
            infoIcon.tag = item
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