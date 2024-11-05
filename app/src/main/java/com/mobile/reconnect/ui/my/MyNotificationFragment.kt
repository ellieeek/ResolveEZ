package com.mobile.reconnect.ui.my

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyBinding
import com.mobile.reconnect.databinding.FragmentMyNotificationBinding
import com.mobile.reconnect.databinding.FragmentMyReportBinding
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.my.adapter.NotificationAdapter
import com.mobile.reconnect.ui.my.viewmodel.NotificationViewModel

class MyNotificationFragment : BaseFragment<FragmentMyNotificationBinding>(R.layout.fragment_my_notification) {

	private val viewModel: NotificationViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.backButton.setOnClickListener {
			requireActivity().onBackPressedDispatcher.onBackPressed()
		}

		val adapter = NotificationAdapter()
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

		viewModel.notificationList.observe(viewLifecycleOwner) { notifications ->
			adapter.submitList(notifications)
		}

		viewModel.fetchAlarms()


	}

}
