package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson2Binding
import com.mobile.reconnect.ui.my.adapter.PrePersonAdapter
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment2 : BaseFragment<FragmentMyPreperson2Binding>(R.layout.fragment_my_preperson2) {
    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var prePersonAdapter: PrePersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment2_to_prepersonFragment3)
        }

        // 뒤로 가기 버튼 설정
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}