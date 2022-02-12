package com.example.dailyplanner.presentation.tasks_list.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.dailyplanner.R
import com.example.dailyplanner.config.AppExt.toHours
import com.example.dailyplanner.databinding.ItemTableDateBinding
import com.example.dailyplanner.databinding.ItemTableTaskBinding
import com.example.dailyplanner.domain.model.Task

class TasksListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var tableItems = mutableListOf<TableItem>()
    private var taskClickListener: OnTaskClickListener? = null

    override fun getItemViewType(position: Int): Int {
        return if (tableItems[position].task == null) TYPE_DATE else TYPE_TASK
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_DATE -> DateViewHolder(inflater.inflate(R.layout.item_table_date, parent, false))
            TYPE_TASK -> TaskViewHolder(inflater.inflate(R.layout.item_table_task, parent, false))
            else -> throw RuntimeException()
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (tableItems[position].task == null) {
            val vh = holder as DateViewHolder
            vh.bindData(tableItems[position].time)
        } else {
            val vh = holder as TaskViewHolder
            vh.bindData(tableItems[position])
        }
    }

    override fun getItemCount(): Int = tableItems.size

    fun setTableItems(data: List<TableItem>) {

        val diffResult = DiffUtil.calculateDiff(DiffCallback(tableItems, data))
        tableItems = data.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }

    fun setTaskClickListener(taskClickListener: OnTaskClickListener) {
        this.taskClickListener = taskClickListener
    }

    private class DiffCallback(val oldList: List<TableItem>, val newList: List<TableItem>) :
        DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldList.size

        override fun getNewListSize(): Int = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].time == newList[newItemPosition].time


        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
            oldList[oldItemPosition].task == newList[newItemPosition].task

    }

    inner class DateViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTableDateBinding.bind(itemView)

        fun bindData(timePeriod: String) {
            binding.tvDate.text = timePeriod
        }
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTableTaskBinding.bind(itemView)

        fun bindData(tableItem: TableItem) {
            with(binding) {
                tvDate.text = tableItem.time
                tvTask.text = tableItem.task!!.name
                tvTime.text = (tableItem.task!!.dateStart to tableItem.task!!.dateEnd).toHours()
                root.setOnClickListener {
                    taskClickListener?.onTaskClicked(tableItem.task!!)
                }
            }
        }
    }

    interface OnTaskClickListener {
        fun onTaskClicked(task: Task)
    }

    companion object {
        private const val TYPE_DATE = 0
        private const val TYPE_TASK = 1
    }
}