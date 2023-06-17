package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.DatePicker
import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentAddItemBinding
import com.example.todoapp.databinding.LaalBinding
import java.time.LocalDate
import java.util.Date


class AddItemFragment : Fragment() {

    lateinit var binding: LaalBinding
    private var deadlineDate: String? = null
    val rep = ToDoItemRepository.getInstance()
    var priority = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LaalBinding.inflate(inflater, container, false)


        binding.priority.setOnClickListener {
            popupMenuShow(it)
        }


        binding.dateSwitch.setOnClickListener {
            openSetDateDialog()
        }


        binding.saveChanges.setOnClickListener {
            saveChanges(priority)
        }

        binding.close.setOnClickListener {
            requireActivity().onBackPressed()
        }

        return binding.root
    }


    private fun popupMenuShow(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.priority_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.low_priority -> {
                    priority = 0
                    true
                }

                R.id.high_priority -> {
                    priority = 2
                    true
                }

                else -> {
                    priority = 1
                    true
                }
            }
        }
        popupMenu.show()
    }


    fun saveChanges(priority: Int) {
        rep.addItem(
            rep.getItems().size.toString(),
            binding.editText.text.toString(),
            priority,
            deadlineDate
        )
        requireActivity().onBackPressed()
    }


    private fun openSetDateDialog() {
        val dialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                run {
                    binding.date.text = LocalDate.of(year, month, dayOfMonth).toStringDate()
                    deadlineDate = LocalDate.of(year, month, dayOfMonth).toStringDate()
                }
            },
            2023,
            6,
            15
        ).show()

    }


    companion object {
        @JvmStatic
        fun newInstance(): AddItemFragment {
            val fragment = AddItemFragment()
            val args = Bundle().apply {

            }
            fragment.arguments = args
            return fragment
        }
    }
}