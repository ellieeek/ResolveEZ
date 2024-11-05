package com.mobile.reconnect.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson5Binding
import com.mobile.reconnect.databinding.FragmentMyPreperson6Binding
import com.mobile.reconnect.databinding.FragmentMyPrepersonBinding
import com.mobile.reconnect.ui.my.adapter.PrePersonAdapter
import com.mobile.reconnect.ui.my.viewmodel.PrePersonViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment6 : BaseFragment<FragmentMyPreperson6Binding>(R.layout.fragment_my_preperson6) {
    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var prePersonAdapter: PrePersonAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        binding.addbtn.setOnClickListener {
//            findNavController().navigate(R.id.action_myPrepersonFragment_to_myPrePersonFragment3)
//        }
    }
}