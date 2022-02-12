package com.example.dailyplanner.presentation.tasks_list

import androidx.lifecycle.viewModelScope
import com.example.dailyplanner.config.AppExt.getDayPeriod
import com.example.dailyplanner.config.AppExt.toHour
import com.example.dailyplanner.config.Constants.ONE_HOUR
import com.example.dailyplanner.data.base.Result
import com.example.dailyplanner.domain.GetTasksUseCase
import com.example.dailyplanner.domain.model.Task
import com.example.dailyplanner.presentation.base.BaseViewModel
import com.example.dailyplanner.presentation.tasks_list.adapter.TableItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class TasksListViewModel @Inject constructor(private val getTasksUseCase: GetTasksUseCase) :
    BaseViewModel<TasksListState, TasksListIntent, TasksListAction>() {
    private var tasks: List<Task>? = null
    private var currentDay: Calendar? = null

    override fun handleIntent(intent: TasksListIntent) {
        when (intent) {
            is TasksListIntent.ScreenEntered -> onScreenEntered(intent.currentDay)
            is TasksListIntent.DayPicked -> getTasksForDay(intent.day)
            is TasksListIntent.TaskPressed -> onTaskPressed(intent.task)
        }
    }

    private fun onScreenEntered(currentDay: Calendar) {
        this.currentDay = currentDay
        if (tasks == null) {
            viewModelScope.launch {
                when (val result = getTasksUseCase.getTasks()) {
                    is Result.Success -> onGetTasksSuccess(result.data)
                    is Result.Error -> onGetTasksError(result.throwable)
                }
            }
        } else {
            getTasksForDay(currentDay)
        }
    }

    private fun getTasksForDay(day: Calendar) {
        tasks?.let { tasksList ->
            val tableItems = getTableItems(tasksList, day)
            mState.value = TasksListState.CurrentTableItems(tableItems)
        }
    }

    private fun getTableItems(tasks: List<Task>, day: Calendar): List<TableItem> {
        val tableItems = mutableListOf<TableItem>()
        val currentDayPeriod = day.getDayPeriod()
        var timePeriod = currentDayPeriod.first
        while (timePeriod < currentDayPeriod.second) {
            val hourTimePeriod = timePeriod to timePeriod + ONE_HOUR
            val tableItem = TableItem(hourTimePeriod.toHour())
            tableItem.task = tasks.firstOrNull {
                it.isTaskInsidePeriod(hourTimePeriod)
            }
            timePeriod += ONE_HOUR
            tableItems.add(tableItem)
        }
        return tableItems
    }

    private fun onTaskPressed(task: Task?) {
        task?.let {
            mActions.value = TasksListAction.NavigateToTaskDetail(task)
        }
    }

    private fun onGetTasksSuccess(data: List<Task>) {
        tasks = data
        currentDay?.let {
            getTasksForDay(it)
        }
    }

    private fun onGetTasksError(e: Exception?) {
        mActions.value = TasksListAction.ErrorReceived(e)
    }
}