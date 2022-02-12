package com.example.dailyplanner.presentation.task_detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.dailyplanner.R
import com.example.dailyplanner.config.AppExt.toPeriod
import com.example.dailyplanner.databinding.FragmentTaskDetailBinding
import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.BaseFragment

class TaskDetailFragment :
    BaseFragment<FragmentTaskDetailBinding, TaskDetailState, TaskDetailIntent, TaskDetailAction, TaskDetailViewModel>(
        R.layout.fragment_task_detail
    ) {

    override val binding: FragmentTaskDetailBinding by viewBinding()
    override val viewModel: TaskDetailViewModel by viewModels()
    private val args: TaskDetailFragmentArgs by navArgs()

    override fun onStart() {
        super.onStart()
        viewModel.sendIntent(TaskDetailIntent.ScreenEntered(args.task))
    }

    private fun setTask(task: Task) {
        with(binding) {
            tvTaskName.text = task.name
            tvTaskStartDate.text = getString(R.string.tasks_detail_start_date).format(task.dateStart.toPeriod())
            tvTaskEndDate.text = getString(R.string.tasks_detail_end_date).format(task.dateEnd.toPeriod())
            tvTaskDescription.text = task.description
        }
    }

    override fun render(state: TaskDetailState) {
        when (state) {
            is TaskDetailState.CurrentTask -> setTask(state.task)
        }
    }

    private fun displayError(e: Exception?) {
        Toast.makeText(context, e?.message, Toast.LENGTH_SHORT).show()
    }

    override fun processAction(action: TaskDetailAction) {
        when (action) {
            is TaskDetailAction.ErrorReceived -> displayError(action.error)
        }
    }
}