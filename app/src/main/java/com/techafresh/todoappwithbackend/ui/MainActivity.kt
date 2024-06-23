package com.techafresh.todoappwithbackend.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.techafresh.todoappwithbackend.backend.ApiService
import com.techafresh.todoappwithbackend.R
import com.techafresh.todoappwithbackend.backend.ApiClient
import com.techafresh.todoappwithbackend.backend.dataClass.Task
import com.techafresh.todoappwithbackend.databinding.ActivityMainBinding
import com.techafresh.todoappwithbackend.ui.adapter.TasksAdapter
import com.techafresh.todoappwithbackend.ui.viewmodel.TasksViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


class MainActivity : AppCompatActivity() {

    lateinit var navHostFragment: NavHostFragment
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: TasksViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[TasksViewModel::class.java]

        navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerViewXX) as NavHostFragment

    }
}