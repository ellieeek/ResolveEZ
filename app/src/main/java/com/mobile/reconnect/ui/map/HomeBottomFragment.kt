package com.mobile.reconnect.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentHomeBottomBinding
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter

class HomeBottomFragment : BottomSheetDialogFragment() {

	private lateinit var binding: FragmentHomeBottomBinding // XML 바인딩
	private lateinit var adapter: MissingPersonsAdapter // 어댑터
	private lateinit var persons: List<MissingPerson> // 데이터 리스트

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBottomBinding.inflate(inflater, container, false)
		setupRecyclerView()

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()
	}

	private fun setupRecyclerView() {

		adapter = MissingPersonsAdapter(persons) { person ->
			Log.d("HomeBottomSheetFragment", "Clicked: ${person.name}")
		}

		binding.rvMissingPersonsList.layoutManager = LinearLayoutManager(requireContext())
		binding.rvMissingPersonsList.adapter = adapter
	}
}
