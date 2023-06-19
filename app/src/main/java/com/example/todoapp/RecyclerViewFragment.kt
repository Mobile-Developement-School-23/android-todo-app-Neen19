package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.fab.setOnClickListener {
            val fragmentAddItem = AddItemFragment.newInstance()
            requireActivity().supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_add_item, fragmentAddItem)
                .commit()
        }
        val adapter = ToDoItemAdapter(object : ItemTouchListener {


            override fun onItemTouch(item: TodoItem) {
                val fragmentAddItem =
                    AddItemFragment.newInstance(
                        AddItemFragment.EDITMODE,
                        item.text,
                        item.priority,
                        item.deadlineDate,
                        item.id.toInt()

                    )
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_add_item, fragmentAddItem)
                    .commit()
            }


        })
        adapter.items = ToDoItemRepository.getInstance().getItems()
        binding.recyclerView.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val done = requireActivity().findViewById<TextView>(R.id.Done)
        val text =
            getString(R.string.completed_title) + ToDoItemRepository.getInstance().getDoneCount()
        done.text = text
    }


    companion object {
        @JvmStatic
        fun newInstance(): RecyclerViewFragment {
            return RecyclerViewFragment()
        }
    }

}


