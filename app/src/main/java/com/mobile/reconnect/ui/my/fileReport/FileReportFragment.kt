package com.mobile.reconnect.ui.my.fileReport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentFileReportBinding
import com.mobile.reconnect.databinding.FragmentFileReportMapBinding
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.software.somding.presentation.common.NavigationUtil.navigate


class FileReportFragment: BaseFragment<FragmentFileReportBinding>(R.layout.fragment_file_report) {
	private val viewModel: MyViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_navigation_file_report_to_navigation_file_report_2)
		}



	}

}