package com.example.todoapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.FragmentAddItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            val fragmentRecycler = RecyclerViewFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_recycler_view, fragmentRecycler)
                .commit()
        }


        binding.fab.setOnClickListener {
            val fragmentAddItem = AddItemFragment.newInstance()
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_add_item,fragmentAddItem)
                .commit()
        }


    }



}