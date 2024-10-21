package com.mobile.reconnect.ui.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentReportBinding
import com.mobile.reconnect.ui.report.adapter.MissingPersonAdapter
import com.mobile.reconnect.ui.report.viewmodel.ReportViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class ReportFragment: BaseFragment<FragmentReportBinding>(R.layout.fragment_report) {
	private val viewModel: ReportViewModel by viewModels()

	private lateinit var missingPersonAdapter: MissingPersonAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// RecyclerView 설정
		setupRecyclerView()

		// ViewModel에서 실종자 목록을 관찰하고 UI에 반영
		viewModel.missingPersonList.observe(viewLifecycleOwner) { missingPersons ->
			missingPersonAdapter.submitList(missingPersons)
		}

		// 실종자 목록을 가져오는 로직 실행
		viewModel.loadMissingPersons()
//
//		viewModel.text.observe(viewLifecycleOwner) {
////			binding.textReport.text = it
//		}
	}

	private fun setupRecyclerView() {
		missingPersonAdapter = MissingPersonAdapter { missingPerson ->
			// 클릭 시 상세 화면으로 이동하는 로직
			// ex: NavigationUtil.navigateToDetail(missingPerson.id)
		}

		binding.recyclerViewReportList.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = missingPersonAdapter
		}
	}

}