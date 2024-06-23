package com.techafresh.todoappwithbackend.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.techafresh.todoappwithbackend.R
import com.techafresh.todoappwithbackend.backend.dataClass.TaskPost
import com.techafresh.todoappwithbackend.databinding.FragmentAddTaskBinding
import com.techafresh.todoappwithbackend.databinding.FragmentAllTasksBinding
import com.techafresh.todoappwithbackend.ui.MainActivity
import com.techafresh.todoappwithbackend.ui.viewmodel.TasksViewModel

class AddTaskFragment : Fragment() {

    private lateinit var binding: FragmentAddTaskBinding
    private lateinit var tasksViewModel: TasksViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_task, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAddTaskBinding.bind(view)
        tasksViewModel = (activity as MainActivity).viewModel

        binding.buttonAddTask.setOnClickListener {
            if (binding.textViewContent.text.isEmpty() || binding.textViewTitle.text.isEmpty()){
                Toast.makeText(activity, "Really Nigga", Toast.LENGTH_SHORT).show()
            }else{
                tasksViewModel.createTask(TaskPost(
                    title = binding.textViewTitle.text.toString(),
                    contents = binding.textViewContent.text.toString()
                ))
                Toast.makeText(activity, "Task Added Successfully", Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_addTaskFragment_to_tasksFragment)
            }
        }

        binding.imageViewBack.setOnClickListener {
            it.findNavController().navigate(R.id.action_addTaskFragment_to_tasksFragment)
            // What if i click add task and i quickly click back what happen to the task does the app crash
        }
    }
}