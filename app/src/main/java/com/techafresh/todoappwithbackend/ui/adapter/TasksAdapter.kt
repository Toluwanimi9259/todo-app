package com.techafresh.todoappwithbackend.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.techafresh.todoappwithbackend.backend.dataClass.Task
import com.techafresh.todoappwithbackend.backend.dataClass.TaskItem
import com.techafresh.todoappwithbackend.databinding.TaskItemBinding
import com.techafresh.todoappwithbackend.ui.viewmodel.TasksViewModel

class TasksAdapter() : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>(){

    private val callback = object : DiffUtil.ItemCallback<TaskItem>(){

        override fun areItemsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskItem, newItem: TaskItem): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this , callback)

    inner class TaskViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(taskItem: TaskItem){
            binding.textViewTaskTitle.text = taskItem.title
            binding.textViewTaskContent.text = taskItem.contents
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TaskViewHolder(binding)
    }

    override fun getItemCount(): Int = differ.currentList.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
        setScaleAnimation(holder.itemView)
    }

    private fun setScaleAnimation(view: View) {
        val anim = ScaleAnimation(
            0.0f,
            1.0f,
            0.0f,
            1.0f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        anim.duration = 500
        view.startAnimation(anim)
    }
}