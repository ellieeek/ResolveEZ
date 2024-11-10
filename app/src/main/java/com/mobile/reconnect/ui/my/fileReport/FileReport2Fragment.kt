package com.mobile.reconnect.ui.my.fileReport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentFileReport2Binding
import com.mobile.reconnect.databinding.FragmentFileReportBinding
import com.mobile.reconnect.databinding.FragmentMyReportBinding
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.mobile.reconnect.ui.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

class FileReport2Fragment: BaseFragment<FragmentFileReport2Binding>(R.layout.fragment_file_report2) {
	private val viewModel: MyViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnLocationRegister.setOnClickListener { navigate(R.id.action_navigation_file_report_2_to_navigation_file_report_map) }

	}

}