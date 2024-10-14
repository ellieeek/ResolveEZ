package com.mobile.reconnect.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyBinding
import com.mobile.reconnect.databinding.FragmentMyNotificationBinding
import com.mobile.reconnect.databinding.FragmentMyReportBinding
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class MyNotificationFragment: BaseFragment<FragmentMyNotificationBinding>(R.layout.fragment_my_notification) {
	private val viewModel: MyViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.backButton.setOnClickListener {
			requireActivity().onBackPressedDispatcher.onBackPressed()
		}
	}

}