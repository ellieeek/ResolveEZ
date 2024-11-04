package com.mobile.reconnect.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.databinding.FragmentSearchFilteringBinding

class SearchFilteringFragment : BottomSheetDialogFragment() {
	private var _binding: FragmentSearchFilteringBinding? = null
	private val binding get() = _binding!!

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		_binding = FragmentSearchFilteringBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//		binding.filteringChipAll.setOnClickListener {
//			dismiss()
//		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
