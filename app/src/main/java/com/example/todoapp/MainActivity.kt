package com.example.todoapp


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        if (savedInstanceState == null) {
            val fragmentRecycler = RecyclerViewFragment.newInstance()
            supportFragmentManager.beginTransaction().add(R.id.fragment_add_item, fragmentRecycler)
                .commit()
        }


    }


}