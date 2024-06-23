package com.techafresh.todoappwithbackend.backend

import com.techafresh.todoappwithbackend.backend.dataClass.Task
import com.techafresh.todoappwithbackend.backend.dataClass.TaskItem
import com.techafresh.todoappwithbackend.backend.dataClass.TaskPost
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("/tasks")
    suspend fun getAllTasks() : Response<Task>

    @POST("/tasks")
    suspend fun createTask(@Body task: TaskPost)

    @GET("/tasks")
    suspend fun getTask(
        @Query("id")
        id : Int
    ) : Response<TaskItem>

    @DELETE("/tasks/{id}")
    suspend fun deleteTask(
        @Path("id")
        id : Int
    ) // To Get Callback


}
