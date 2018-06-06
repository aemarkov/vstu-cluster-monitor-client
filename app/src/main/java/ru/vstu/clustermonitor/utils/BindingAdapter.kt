package ru.vstu.clustermonitor.utils

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup


abstract class BindingAdapter<in TItem, TBinding: ViewDataBinding>(private val _data: List<TItem>,
                                                                   private val _layout: Int,
                                                                   private val onClick: ((TItem)->Unit)?):
        RecyclerView.Adapter<ViewHolder<TBinding>>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<TBinding> {
        //val binding = DataBindingUtil.bind<TBinding>(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<TBinding>(layoutInflater, _layout, parent, false)

        if(binding==null)
            throw Exception("WTF happened")

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder<TBinding>, position: Int) {
        val item = _data[position]
        setBinding(holder.binding, item)
        holder.itemView.setOnClickListener {
            onClick?.invoke(item)
        }
    }

    override fun getItemCount(): Int = _data.size

    /**
     * Set data to the binding object MyViewBinding
     */
    abstract fun setBinding(binding:TBinding, item: TItem)
}

class ViewHolder<TBinding: ViewDataBinding>(val binding: TBinding): RecyclerView.ViewHolder(binding.root)
{
}