package ru.vstu.clustermonitor.Views.Adapters

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vstu.clustermonitor.Models.QueueTask
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.databinding.QueueTaskItemBinding

class QueueTaskViewHolder(val binding: QueueTaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: QueueTask){
        binding.task = task
        binding.executePendingBindings()
    }
}

class QueueTasksAdapter(val data: List<QueueTask>) : RecyclerView.Adapter<QueueTaskViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<QueueTaskItemBinding>(layoutInflater, R.layout.queue_task_item, parent, false)
        return QueueTaskViewHolder(binding)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: QueueTaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

}