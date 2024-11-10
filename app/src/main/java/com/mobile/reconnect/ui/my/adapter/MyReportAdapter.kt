package com.mobile.reconnect.ui.my.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.data.model.MissingPerson_ex
import com.mobile.reconnect.data.model.MyReportList
import com.mobile.reconnect.databinding.ItemMissingPersonBinding
import com.mobile.reconnect.databinding.ItemMyReportListBinding

class MyReportAdapter(
	private val persons: List<MyReportList>,
	private val onItemClicked: (MyReportList) -> Unit
) : RecyclerView.Adapter<MyReportAdapter.MyReportViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyReportViewHolder {
		val binding =
			ItemMyReportListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MyReportViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MyReportViewHolder, position: Int) {
		val person = persons[position]
		holder.bind(person)

		holder.itemView.setOnClickListener {
			onItemClicked(person)
		}
	}

	override fun getItemCount(): Int = persons.size

	class MyReportViewHolder(private val binding: ItemMyReportListBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(person: MyReportList) {
			binding.tvName.text = person.name
			binding.tvStatus.text = person.status.toString()
			binding.tvDate.text = person.date
		}
	}
}
