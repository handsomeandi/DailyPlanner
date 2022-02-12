package com.example.dailyplanner.presentation.task_detail

import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.State
import com.example.dailyplanner.presentation.base.ViewAction
import com.example.dailyplanner.presentation.base.ViewIntent

sealed class TaskDetailState : State {
    class CurrentTask(val task: Task) : TaskDetailState()
}

sealed class TaskDetailIntent : ViewIntent {
    class ScreenEntered(val task: Task?) : TaskDetailIntent()
}

sealed class TaskDetailAction : ViewAction {
    class ErrorReceived(val error: Exception?) : TaskDetailAction()
}