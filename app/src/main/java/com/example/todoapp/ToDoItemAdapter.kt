package com.example.todoapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ToDoItemBinding


interface ItemTouchListener {

    fun onItemTouch(item: TodoItem) {}


}


class ToDoItemAdapter(private val listener: ItemTouchListener) :
    RecyclerView.Adapter<ToDoItemAdapter.ToDoItemViewHolder>(
    ), View.OnClickListener {


    override fun onClick(v: View?) {
        val item = v?.tag as TodoItem
        listener.onItemTouch(item)
        notifyDataSetChanged()
    }

    var items: List<TodoItem> = ToDoItemRepository.getInstance().getItems()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ToDoItemBinding.inflate(inflater, parent, false)
        binding.infoIcon.setOnClickListener(this)
        return ToDoItemViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            holder.itemView.tag = item
            infoIcon.tag = item
            task.text = item.text
            Log.i("tag", item.deadlineDate.toString())
            if (item.deadlineDate != null) {
                dateText.visibility = View.VISIBLE
                dateText.text = item.deadlineDate.toString()
            } else {
                dateText.visibility = View.GONE
            }
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