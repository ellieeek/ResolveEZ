package com.mobile.reconnect.ui.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.data.model.search.MissingPerson
import com.mobile.reconnect.data.model.MissingPerson_ex
import com.mobile.reconnect.databinding.ItemMissingPersonBinding

class MissingPersonsAdapter(
	private val onItemClicked: (MissingPerson) -> Unit
) : ListAdapter<MissingPerson, MissingPersonsAdapter.MissingPersonViewHolder>(
	MissingPersonDiffCallback()
) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissingPersonViewHolder {
		val binding =
			ItemMissingPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MissingPersonViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MissingPersonViewHolder, position: Int) {
		val person = getItem(position)
		holder.bind(person)

		holder.itemView.setOnClickListener {
			onItemClicked(person)
		}
	}

	class MissingPersonViewHolder(private val binding: ItemMissingPersonBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(person: MissingPerson) {
			binding.tvPersonName.text = person.name
//			binding.tvPersonInfo.text = person.info
//			binding.tvPersonDetail.text = person.detail
		}
	}
}

class MissingPersonDiffCallback : DiffUtil.ItemCallback<MissingPerson>() {
	override fun areItemsTheSame(oldItem: MissingPerson, newItem: MissingPerson): Boolean {
		return oldItem.id == newItem.id // id로 비교
	}

	override fun areContentsTheSame(oldItem: MissingPerson, newItem: MissingPerson): Boolean {
		return oldItem == newItem // 전체 내용이 동일한지 비교
	}
}
