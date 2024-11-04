package com.mobile.reconnect.ui.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
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
		missingPersonAdapter = MissingPersonAdapter { missingPerson, view ->
			when (view.id) {
				R.id.btnReport -> {
					// ReportRegistrationFragment로 이동
					findNavController().navigate(R.id.action_reportFragment_to_reportRegistrationFragment)
				}
				else -> {
					// ReportDetailFragment로 이동 (missingPerson.id 전달)
					val bundle = Bundle().apply {
//						putString("missingPersonId", MissingPerson)
					}
					findNavController().navigate(R.id.action_reportFragment_to_reportDetailFragment, bundle)
				}
			}
		}

		binding.recyclerViewReportList.apply {
			layoutManager = LinearLayoutManager(context)
			adapter = missingPersonAdapter
		}
	}

}