package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPrepersonBinding
import com.mobile.reconnect.ui.my.adapter.PrePersonAdapter
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment : BaseFragment<FragmentMyPrepersonBinding>(R.layout.fragment_my_preperson) {
    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var prePersonAdapter: PrePersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 어댑터 초기화
        prePersonAdapter = PrePersonAdapter { prePerson ->
            // 아이템 클릭 시 동작 정의
        }

        // 리사이클러뷰 설정
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = prePersonAdapter
        }

        viewModel.prePersons.observe(viewLifecycleOwner) { prePersons ->
            prePersonAdapter.submitList(prePersons)
        }

        // 예시 데이터
        viewModel.fetchPrePersons()

        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment_to_prepersonFragment2)
        }
        binding.addbtn2.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment_to_prepersonFragment2)
        }

        // 뒤로 가기 버튼 설정
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}