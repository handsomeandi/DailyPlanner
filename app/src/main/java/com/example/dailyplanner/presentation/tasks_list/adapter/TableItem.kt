package com.example.dailyplanner.presentation.tasks_list.adapter

import com.example.dailyplanner.domain.model.Task

data class TableItem(
    val time: String,
    var task: Task? = null
)