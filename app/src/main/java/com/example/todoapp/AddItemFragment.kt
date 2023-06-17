package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentAddItemBinding
import java.time.LocalDate


class AddItemFragment : Fragment() {

    lateinit var binding: FragmentAddItemBinding
    private var deadlineDate: String? = null
    val rep = ToDoItemRepository.getInstance()
    var priority = 1
    var mode: Int? = ADDMODE

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddItemBinding.inflate(inflater, container, false)


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

        binding.editText.setText(this.arguments?.getString(TEXT))
        binding.date.setText(
            this.arguments?.getString(DEADLINE)
        )
        mode = arguments?.getInt(MODE)

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
        when (mode) {
            EDITMODE -> {
                rep.editItem(
                    this.requireArguments().getInt(POSITION),
                    binding.editText.text.toString(),
                    priority,
                    deadlineDate
                )
                Log.i(tag, "edit")
            }

            else -> {
                rep.addItem(
                    rep.getItems().size.toString(),
                    binding.editText.text.toString(),
                    priority,
                    deadlineDate
                )
                Log.i(tag, "add")
            }
        }
        requireActivity().onBackPressed()
    }


    private fun openSetDateDialog() {
        DatePickerDialog(
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

        val MODE: String = "mode"
        val EDITMODE: Int = 2
        val ADDMODE: Int = 1

        val PRIORITY: String = "priority_key"
        val DEADLINE: String = "deadline_key"
        val TEXT: String = "text_key"
        val POSITION: String = "position_key"

        @JvmStatic
        fun newInstance(
            mode: Int = ADDMODE,
            text: String? = null,
            priority: Int = 1,
            deadlineDate: String? = null,
            position: Int = -1
        ): AddItemFragment {
            val fragment = AddItemFragment()
            val args = Bundle().apply {
                putInt(PRIORITY, priority)
                putString(TEXT, text)
                putString(DEADLINE, deadlineDate)
                putInt(MODE, mode)
                putInt(POSITION, position)
            }
            fragment.arguments = args
            return fragment
        }
    }
}