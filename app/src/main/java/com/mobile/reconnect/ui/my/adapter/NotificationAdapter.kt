package com.mobile.reconnect.ui.my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MyNotificationList
import com.mobile.reconnect.databinding.ItemMyNotiListBinding

class NotificationAdapter : ListAdapter<MyNotificationList, NotificationAdapter.AlarmViewHolder>(AlarmDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val binding: ItemMyNotiListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_my_noti_list,
            parent,
            false
        )
        return AlarmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class AlarmViewHolder(private val binding: ItemMyNotiListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(alarm: MyNotificationList) {
            binding.item = alarm

            val context = binding.chip.context
            val colorRes = if (alarm.status == "실종") R.color.primary_red else R.color.blue
            binding.chip.setChipBackgroundColorResource(colorRes)

            binding.executePendingBindings()
        }
    }
}

class AlarmDiffCallback : DiffUtil.ItemCallback<MyNotificationList>() {
    override fun areItemsTheSame(oldItem: MyNotificationList, newItem: MyNotificationList): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: MyNotificationList, newItem: MyNotificationList): Boolean = oldItem == newItem
}
