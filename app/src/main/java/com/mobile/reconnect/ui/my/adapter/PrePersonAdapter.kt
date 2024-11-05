package com.mobile.reconnect.ui.my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.databinding.ItemMyPrepersonListBinding
import com.mobile.reconnect.data.model.PrePerson

class PrePersonAdapter(private val onClick: (PrePerson) -> Unit) :
    ListAdapter<PrePerson, PrePersonAdapter.PrePersonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrePersonViewHolder {
        val binding = ItemMyPrepersonListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PrePersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PrePersonViewHolder, position: Int) {
        val prePerson = getItem(position)
        holder.bind(prePerson, onClick)
    }

    class PrePersonViewHolder(private val binding: ItemMyPrepersonListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(prePerson: PrePerson, onClick: (PrePerson) -> Unit) {
            binding.item = prePerson  // XML의 'item' 변수에 데이터 연결
            binding.root.setOnClickListener { onClick(prePerson) }
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PrePerson>() {
        override fun areItemsTheSame(oldItem: PrePerson, newItem: PrePerson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PrePerson, newItem: PrePerson): Boolean {
            return oldItem == newItem
        }
    }
}