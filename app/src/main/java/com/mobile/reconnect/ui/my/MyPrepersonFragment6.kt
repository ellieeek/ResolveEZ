package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson5Binding
import com.mobile.reconnect.databinding.FragmentMyPreperson6Binding
import com.mobile.reconnect.databinding.FragmentMyPrepersonBinding
import com.mobile.reconnect.ui.my.adapter.PrePersonAdapter
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment6 : BaseFragment<FragmentMyPreperson6Binding>(R.layout.fragment_my_preperson6) {
    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var prePersonAdapter: PrePersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment6_to_prepersonFragment7)
        }

        // 뒤로 가기 버튼 설정
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // 체크박스 상태에 따라 텍스트 색상 변경
        setupCheckBoxListeners()
    }


    private fun setupCheckBoxListeners() {
        // 모든 체크박스를 변수에 저장
        val checkboxes = listOf(
            binding.checkboxNonDisabledMinor,
            binding.checkboxDisabledPerson,
            binding.checkboxDementiaPatient,
            binding.checkboxRunaway,
            binding.checkboxOther
        )

        // "없음" 체크박스
        val checkboxNone = binding.checkboxNone

        // "없음" 체크박스 리스너
        checkboxNone.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                // "없음" 선택 시 다른 체크박스 해제
                checkboxes.forEach { it.isChecked = false }
                checkboxNone.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_800
                    )
                )
            } else {
                checkboxNone.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.gray_600
                    )
                )
            }
        }

        // 다른 체크박스들 리스너 설정
        checkboxes.forEach { checkbox ->
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    // 다른 체크박스 선택 시 "없음" 체크 해제
                    checkboxNone.isChecked = false
                    checkbox.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_800
                        )
                    )
                } else {
                    checkbox.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.gray_600
                        )
                    )
                }
            }
        }
    }
}