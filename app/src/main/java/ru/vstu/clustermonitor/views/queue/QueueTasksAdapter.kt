package ru.vstu.clustermonitor.views.queue

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.vstu.clustermonitor.models.QueueTask
import ru.vstu.clustermonitor.R
import ru.vstu.clustermonitor.databinding.QueueTaskItemBinding


class QueueTasksAdapter(val data: List<QueueTask>, val itemClick: (QueueTask)->Unit) : RecyclerView.Adapter<QueueTaskViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueueTaskViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<QueueTaskItemBinding>(layoutInflater, R.layout.queue_task_item, parent, false)
        return QueueTaskViewHolder(binding)
    }

    override fun getItemCount(): Int  = data.size

    override fun onBindViewHolder(holder: QueueTaskViewHolder, position: Int) {
        holder.bind(data[position], itemClick)
    }

}

class QueueTaskViewHolder(val binding: QueueTaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(task: QueueTask, itemClick: (QueueTask) -> Unit){
        binding.task = task
        binding.executePendingBindings()

        itemView.setOnClickListener{itemClick(task)}
    }
}