package com.mobile.reconnect.ui.report.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.ItemMissingPersonBinding

// RecyclerView에서 실종자 목록 표시를 위한 어댑터 (실종자 데이터 받아 각 항목 구성)
class MissingPersonAdapter(private val onClick: (MissingPerson, View) -> Unit) : ListAdapter<MissingPerson, MissingPersonAdapter.MissingPersonViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissingPersonViewHolder {
        val binding = ItemMissingPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MissingPersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MissingPersonViewHolder, position: Int) {
        val missingPerson = getItem(position)
        holder.bind(missingPerson, onClick)
    }

    class MissingPersonViewHolder(private val binding: ItemMissingPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(missingPerson: MissingPerson, onClick: (MissingPerson, View) -> Unit) {
            binding.missingPerson = missingPerson // 데이터 바인딩

            // 아이템 전체 클릭 시 ReportDetailFragment로 이동
            binding.root.setOnClickListener {
                onClick(missingPerson, it)
            }

            // 버튼 클릭 시 ReportRegistrationFragment로 이동
            binding.btnReport.setOnClickListener {
                onClick(missingPerson, it)
            }

            // 즉시 데이터 바인딩을 반영
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MissingPerson>() {
        override fun areItemsTheSame(oldItem: MissingPerson, newItem: MissingPerson): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MissingPerson, newItem: MissingPerson): Boolean {
            return oldItem == newItem
        }
    }
}