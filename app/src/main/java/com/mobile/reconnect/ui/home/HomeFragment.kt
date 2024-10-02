package com.mobile.reconnect.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentHomeBinding
import com.mobile.reconnect.ui.home.viewmodel.HomeViewModel
import com.software.somding.presentation.common.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
	private val viewModel: HomeViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.text.observe(viewLifecycleOwner) {
			binding.textHome.text = it
		}
	}
}