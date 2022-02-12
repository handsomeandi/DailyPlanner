package com.example.dailyplanner.presentation.tasks_list

import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.State
import com.example.dailyplanner.presentation.base.ViewAction
import com.example.dailyplanner.presentation.base.ViewIntent
import com.example.dailyplanner.presentation.tasks_list.adapter.TableItem
import java.util.*

sealed class TasksListState : State {
    class CurrentTableItems(val tableItems: List<TableItem>) : TasksListState()
}

sealed class TasksListIntent : ViewIntent {
    class ScreenEntered(val currentDay: Calendar) : TasksListIntent()
    class DayPicked(val day: Calendar) : TasksListIntent()
    class TaskPressed(val task: Task) : TasksListIntent()
}

sealed class TasksListAction : ViewAction {
    class NavigateToTaskDetail(val task: Task) : TasksListAction()
    class ErrorReceived(val error: Exception?) : TasksListAction()
}