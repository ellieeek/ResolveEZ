package com.mobile.reconnect.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.chip.Chip
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentSearchFilteringBinding
import androidx.core.content.ContextCompat
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

		binding.btnMale.setOnClickListener {
			selectGender(binding.btnMale, binding.btnFemale)
		}

		binding.btnFemale.setOnClickListener {
			selectGender(binding.btnFemale, binding.btnMale)
		}

		setChipClickListener(binding.btnNonDisabled, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnDementia, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnRunaway, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnDisabled, R.color.primary_red, R.color.gray_300)
		setChipClickListener(binding.btnEtc, R.color.primary_red, R.color.gray_300)

		binding.btnCancel.setOnClickListener {
			dismiss()
		}
	}

	private fun selectGender(selectedChip: Chip, unselectedChip: Chip) {
		val selectedColor = ContextCompat.getColor(requireContext(), R.color.primary_red)
		val unselectedColor = ContextCompat.getColor(requireContext(), R.color.gray_300)

		val currentStrokeColor = selectedChip.chipStrokeColor?.defaultColor

		if (currentStrokeColor == selectedColor) {
			selectedChip.setChipStrokeColorResource(R.color.gray_300)
			selectedChip.isChecked = false
		} else {
			selectedChip.setChipStrokeColorResource(R.color.primary_red)
			selectedChip.isChecked = true
		}

		unselectedChip.setChipStrokeColorResource(R.color.gray_300)
		unselectedChip.isChecked = false
	}

	// Chip 클릭 시 색상 변경 함수
	private fun setChipClickListener(chip: Chip, selectedColor: Int, unselectedColor: Int) {
		chip.setOnClickListener {
			val currentStrokeColor = chip.chipStrokeColor?.defaultColor

			if (currentStrokeColor == ContextCompat.getColor(requireContext(), selectedColor)) {
				chip.setChipStrokeColorResource(unselectedColor)
				chip.setTextColor(ContextCompat.getColor(requireContext(), R.color.gray_700))
			} else {
				chip.setChipStrokeColorResource(selectedColor)
				chip.setTextColor(ContextCompat.getColor(requireContext(), selectedColor))
			}

			chip.isChecked = !chip.isChecked
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
}
