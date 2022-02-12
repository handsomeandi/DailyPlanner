package com.example.dailyplanner.presentation.task_detail

import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TaskDetailViewModel @Inject constructor() :
    BaseViewModel<TaskDetailState, TaskDetailIntent, TaskDetailAction>() {
    override fun handleIntent(intent: TaskDetailIntent) {
        when (intent) {
            is TaskDetailIntent.ScreenEntered -> setTask(intent.task)
        }
    }

    private fun setTask(task: Task?) {
        task?.let {
            mState.value = TaskDetailState.CurrentTask(task)
        }
    }


}