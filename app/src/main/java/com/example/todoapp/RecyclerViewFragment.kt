package com.example.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.FragmentRecyclerViewBinding


class RecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentRecyclerViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerViewBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = ToDoItemAdapter()
        adapter.items = ToDoItemRepository.getInstance().getItems()
        binding.recyclerView.adapter = adapter
        return binding.root
    }


    companion object {
        @JvmStatic
        fun newInstance(): RecyclerViewFragment {
            return RecyclerViewFragment()
        }
    }
}