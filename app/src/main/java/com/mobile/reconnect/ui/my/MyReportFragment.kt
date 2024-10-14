package com.mobile.reconnect.ui.my

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyBinding
import com.mobile.reconnect.databinding.FragmentMyReportBinding
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class MyReportFragment: BaseFragment<FragmentMyReportBinding>(R.layout.fragment_my_report) {
	private val viewModel: MyViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)


	}

}