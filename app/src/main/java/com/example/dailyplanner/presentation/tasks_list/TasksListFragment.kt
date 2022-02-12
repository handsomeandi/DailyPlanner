package com.example.dailyplanner.presentation.tasks_list

import android.widget.Toast
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dailyplanner.R
import com.example.dailyplanner.databinding.FragmentTasksListBinding
import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.BaseFragment
import com.example.dailyplanner.presentation.tasks_list.adapter.TableItem
import com.example.dailyplanner.presentation.tasks_list.adapter.TasksListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksListFragment :
    BaseFragment<FragmentTasksListBinding, TasksListState, TasksListIntent, TasksListAction, TasksListViewModel>(
        R.layout.fragment_tasks_list
    ) {

    override val binding: FragmentTasksListBinding by viewBinding()
    override val viewModel: TasksListViewModel by viewModels()
    private val adapter = TasksListAdapter()

    override fun onStart() {
        super.onStart()
        initView()
    }

    private fun initView() {
        adapter.setTaskClickListener(object : TasksListAdapter.OnTaskClickListener {
            override fun onTaskClicked(task: Task) {
                viewModel.sendIntent(TasksListIntent.TaskPressed(task))
            }
        })
        with(binding){
            viewModel.sendIntent(TasksListIntent.ScreenEntered(cvCalendar.selectedDates[0]))
            rvTasksList.adapter = adapter
            cvCalendar.setOnDayClickListener {
                viewModel.sendIntent(TasksListIntent.DayPicked(it.calendar))
            }
        }

    }

    private fun setTableItems(tableItems: List<TableItem>) {
        adapter.setTableItems(tableItems)
    }

    override fun render(state: TasksListState) {
        when (state) {
            is TasksListState.CurrentTableItems -> setTableItems(state.tableItems)
        }
    }

    private fun displayError(e: Exception?) {
        Toast.makeText(context, e?.message, Toast.LENGTH_SHORT).show()
    }

    private fun openDetailScreen(task: Task?) {
        val action =
            TasksListFragmentDirections.actionTasksListFragmentToTaskDetailFragment().apply {
                setTask(task)
            }
        navController.navigate(action)
    }

    override fun processAction(action: TasksListAction) {
        when (action) {
            is TasksListAction.ErrorReceived -> displayError(action.error)
            is TasksListAction.NavigateToTaskDetail -> openDetailScreen(action.task)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = TasksListFragment()
    }
}