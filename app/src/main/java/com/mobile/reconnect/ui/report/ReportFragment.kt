package com.mobile.reconnect.ui.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentReportBinding
import com.mobile.reconnect.ui.report.viewmodel.ReportViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class ReportFragment: BaseFragment<FragmentReportBinding>(R.layout.fragment_report) {
	private val viewModel: ReportViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.text.observe(viewLifecycleOwner) {
			binding.textReport.text = it
		}
	}

}