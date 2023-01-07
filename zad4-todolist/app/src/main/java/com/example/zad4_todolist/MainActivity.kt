package com.example.zad4_todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zad4_todolist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var _binding: ActivityMainBinding
//    private val binding get() = _binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
    }
}