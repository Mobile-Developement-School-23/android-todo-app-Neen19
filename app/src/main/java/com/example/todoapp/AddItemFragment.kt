package com.example.todoapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.todoapp.databinding.FragmentAddItemBinding
import java.time.LocalDate


class AddItemFragment : Fragment() {

    private lateinit var binding: FragmentAddItemBinding
    private var deadlineDate: String? = null
    private val rep = ToDoItemRepository.getInstance()
    private var priority = Priority.NO_PRIORITY
    private var mode: Int? = ADDMODE

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
            openRecyclerFragment()
        }

//        binding.deleteButton.setOnClickListener {
//            deleteItem(this.requireArguments().getInt(POSITION))
//        }

        binding.editText.setText(this.arguments?.getString(TEXT))
        binding.date.text = this.arguments?.getString(DEADLINE)
        mode = arguments?.getInt(MODE)

        return binding.root
    }


    private fun popupMenuShow(view: View) {
        val popupMenu = PopupMenu(requireContext(), view)
        popupMenu.inflate(R.menu.priority_menu)
        popupMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.low_priority -> {
                    priority = Priority.LOW_PRIORITY
                    binding.priority.text = getString(R.string.low_priority)
                    true
                }

                R.id.high_priority -> {
                    priority = Priority.HIGH_PRIORITY
                    binding.priority.text = getString(R.string.high_priority)
                    true
                }

                else -> {
                    priority = Priority.NO_PRIORITY
                    binding.priority.text = getString(R.string.no_priority)
                    true
                }
            }
        }
        popupMenu.show()
    }


    private fun saveChanges(priority: Priority) {
        when (mode) {
            EDITMODE -> {
                rep.editItem(
                    this.requireArguments().getInt(POSITION),
                    binding.editText.text.toString(),
                    priority,
                    deadlineDate
                )
            }

            else -> {
                rep.addItem(
                    rep.getItems().size.toString(),
                    binding.editText.text.toString(),
                    priority,
                    deadlineDate
                )
            }
        }
        openRecyclerFragment()
    }


//    private fun deleteItem(position: Int) {
//        if (mode == EDITMODE) {
//            openRecyclerFragment(position)
//        } else {
//            openRecyclerFragment()
//        }
//
//    }

    private fun openSetDateDialog() {
        DatePickerDialog(
            requireContext(),
            { _, year, month, dayOfMonth ->
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


    private fun openRecyclerFragment(position: Int = -1) {
        val fragmentRecycler = RecyclerViewFragment.newInstance(position)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragment_add_item, fragmentRecycler)
            .commit()
    }


    companion object {

        const val MODE: String = "mode"
        const val EDITMODE: Int = 2
        const val ADDMODE: Int = 1

        private const val PRIORITY: String = "priority_key"
        const val DEADLINE: String = "deadline_key"
        const val TEXT: String = "text_key"
        const val POSITION: String = "position_key"

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