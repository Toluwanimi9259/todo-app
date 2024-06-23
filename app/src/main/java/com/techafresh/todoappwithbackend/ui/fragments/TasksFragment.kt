package com.techafresh.todoappwithbackend.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.techafresh.todoappwithbackend.R
import com.techafresh.todoappwithbackend.backend.dataClass.TaskItem
import com.techafresh.todoappwithbackend.backend.dataClass.TaskPost
import com.techafresh.todoappwithbackend.databinding.FragmentAllTasksBinding
import com.techafresh.todoappwithbackend.ui.MainActivity
import com.techafresh.todoappwithbackend.ui.adapter.TasksAdapter
import com.techafresh.todoappwithbackend.ui.viewmodel.TasksViewModel


class TasksFragment : Fragment() {
    private lateinit var binding: FragmentAllTasksBinding
    private lateinit var viewModel: TasksViewModel
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_tasks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllTasksBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        tasksAdapter = TasksAdapter()

        viewModel.getAllTasksFromBackend()
        viewModel.allTasks.observe(viewLifecycleOwner , Observer {
            Toast.makeText(activity, it[1].title, Toast.LENGTH_LONG).show()
            tasksAdapter.differ.submitList(it)
            initRV(tasksAdapter)
        })

        binding.floatingActionButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_tasksFragment_to_addTaskFragment)
//            createTask()
        }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val task = tasksAdapter.differ.currentList[position]
                viewModel.deleteTask(task.id)
                Snackbar.make(view, "Task Deleted Successfully!!" , Snackbar.LENGTH_LONG)
                    .apply {
                        setAction("Undo"){
                            viewModel.createTask(
                                TaskPost(
                                contents = task.contents,
                                title = task.title
                            )
                            )
                            tasksAdapter.notifyDataSetChanged()
                        }
                        show()
                    }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(binding.tasksRV)
        }
    }

    private fun initRV(tasksAdapter: TasksAdapter){
        binding.tasksRV.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun createTask(){
        val view = layoutInflater.inflate(R.layout.add_task_layout, null)
        val builder = activity?.let { AlertDialog.Builder(it.applicationContext) }
        builder?.setView(view)?.show()
    }
}