package com.example.recyclerviewrom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewrom.databinding.ActivityMainBinding
import com.example.recyclerviewrom.databinding.ItemUserBinding
import com.example.recyclerviewrom.model.UsersListener
import com.example.recyclerviewrom.model.UsersService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UsersAdapter

    private val usersService:UsersService
    get() = (applicationContext as App).usersService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UsersAdapter()

        var layoutManager = LinearLayoutManager(this)
        binding.rvList.layoutManager = layoutManager
        binding.rvList.adapter = adapter

        usersService.addListener(usersListener)

    }

    override fun onDestroy() {
        super.onDestroy()
        usersService.removeListener(usersListener)
    }

    private val usersListener:UsersListener = {
        adapter.users = it
    }



}