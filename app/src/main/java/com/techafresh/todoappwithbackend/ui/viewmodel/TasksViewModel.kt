package com.techafresh.todoappwithbackend.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.techafresh.todoappwithbackend.backend.ApiClient
import com.techafresh.todoappwithbackend.backend.ApiService
import com.techafresh.todoappwithbackend.backend.dataClass.Task
import com.techafresh.todoappwithbackend.backend.dataClass.TaskPost
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TasksViewModel() : ViewModel() {
    var allTasks = MutableLiveData<Task>()
    private var retrofit: Retrofit = ApiClient.getApiClient()!!
    private var apiService: ApiService = retrofit.create(ApiService::class.java)

    init {
        //        getAllTasksFromBackend()
    }

    fun getAllTasksFromBackend() = viewModelScope.launch {
        val tasksFromBackend = apiService.getAllTasks()
        try {
            allTasks.value = tasksFromBackend.body()
            Log.d("Result" , tasksFromBackend.body().toString())
        }catch (ex : Exception){
            Log.d("MYTAG" , "Well Shiit")
        }
    }

    fun createTask(task: TaskPost){
        viewModelScope.launch {
            apiService.createTask(task)
        }
    }

    fun getTask(id : Int){
        viewModelScope.launch {
            apiService.getTask(id)
        }
    }

    fun deleteTask(id: Int){
//        var data = "NULL"
        viewModelScope.launch {
            apiService.deleteTask(id)
//                .enqueue(object : Callback<String>{
//
//                override fun onFailure(call: Call<String>, t: Throwable) {
//                    data = "Failure"
//                }
//
//                override fun onResponse(call: Call<String>, response: Response<String>) {
//                    data = response.body().toString()
//                }
//            })
        }
//        return data
    }
}