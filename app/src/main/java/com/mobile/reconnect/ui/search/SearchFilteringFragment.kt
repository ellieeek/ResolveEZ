package com.mobile.reconnect.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentSearchFilteringBinding

class SearchFilteringFragment : BottomSheetDialogFragment() {
	private var _binding: FragmentSearchFilteringBinding? = null
	private var selectedButton: View? = null  // 현재 선택된 버튼
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

	private fun updateSelectedCategory(selectedBtn: View) {
		// 기존에 선택된 버튼의 배경을 원래 상태로 되돌림
//		selectedButton?.setBackgroundResource(R.drawable.bg_line_gray_radius_10)

		// 새로 선택된 버튼에 배경색 적용
//		selectedBtn.setBackgroundResource(R.drawable.bg_shared_magenta_10)

		// 선택된 버튼을 현재 버튼으로 갱신
		selectedButton = selectedBtn
	}
}
