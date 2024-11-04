package com.mobile.reconnect.ui.map.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.data.model.MissingPerson_ex
import com.mobile.reconnect.databinding.ItemMissingPersonBinding

class MissingPersonsAdapter(
	private val persons: List<MissingPerson_ex>,
	private val onItemClicked: (MissingPerson_ex) -> Unit
) : RecyclerView.Adapter<MissingPersonsAdapter.MissingPersonViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MissingPersonViewHolder {
		val binding =
			ItemMissingPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MissingPersonViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MissingPersonViewHolder, position: Int) {
		val person = persons[position]
		holder.bind(person)

		holder.itemView.setOnClickListener {
			onItemClicked(person)
		}
	}

	override fun getItemCount(): Int = persons.size

	class MissingPersonViewHolder(private val binding: ItemMissingPersonBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(person: MissingPerson_ex) {
			binding.tvPersonName.text = person.name
			binding.tvPersonInfo.text = person.info
			binding.tvPersonDetail.text = person.detail
		}
	}
}
