package com.mobile.reconnect.ui.search

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.chip.Chip
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentSearchBinding
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.search.viewmodel.SearchViewModel
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
	private val viewModel: SearchViewModel by viewModels()

	private lateinit var adapter: MissingPersonsAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()

		// 검색 뷰 설정
		binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String?): Boolean {
				query?.let {
					viewModel.searchMissingPersons(it)
				}
				return true
			}

			override fun onQueryTextChange(newText: String?): Boolean {
				return false
			}
		})

		// 필터링 버튼 클릭 리스너
		binding.filteringChipAll.setOnClickListener {
			val filterBottomSheet = SearchFilteringFragment()
			filterBottomSheet.show(childFragmentManager, filterBottomSheet.tag)
		}

		setChipClickListener(binding.btnAge, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnDefail, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnGender, R.color.primary_red, R.color.gray_300)

		// ViewModel 상태에 따라 UI 업데이트
		viewModel.missingPersons.observe(viewLifecycleOwner) { persons ->
			adapter.submitList(persons)
		}

		// 에러 처리
		viewModel.error.observe(viewLifecycleOwner) {
			// 에러 메시지 처리 (예: Toast 또는 Snackbar)
		}
	}

	private fun setupRecyclerView() {
		adapter = MissingPersonsAdapter { person ->
			Log.d("SearchFragment", "Clicked: ${person.name}")
		}

		binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerView.adapter = adapter
	}

	// Chip 클릭 시 색상 변경 함수
	private fun setChipClickListener(chip: Chip, selectedColor: Int, unselectedColor: Int) {
		chip.setOnClickListener {
			val currentStrokeColor = chip.chipStrokeColor?.defaultColor

			if (currentStrokeColor == ContextCompat.getColor(requireContext(), selectedColor)) {
				chip.setChipStrokeColorResource(unselectedColor)
				chip.setTextColor(ContextCompat.getColor(requireContext(),  R.color.gray_600))
			} else {
				chip.setChipStrokeColorResource(selectedColor)
				chip.setTextColor(ContextCompat.getColor(requireContext(), selectedColor))
			}

			chip.isChecked = !chip.isChecked
		}
	}
}
