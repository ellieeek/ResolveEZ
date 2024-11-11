package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson7Binding
import com.mobile.reconnect.databinding.FragmentMyPreperson8Binding
import com.mobile.reconnect.databinding.FragmentMyPrepersonBinding
import com.mobile.reconnect.ui.my.adapter.PrePersonAdapter
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment8 : BaseFragment<FragmentMyPreperson8Binding>(R.layout.fragment_my_preperson8) {
    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var prePersonAdapter: PrePersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment8_to_prepersonFragment9)
        }

        // 뒤로 가기 버튼 설정
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}