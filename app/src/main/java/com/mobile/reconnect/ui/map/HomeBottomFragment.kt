package com.mobile.reconnect.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentHomeBottomBinding
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter
import com.mobile.reconnect.ui.map.viewmodel.HomeBottomViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomFragment: BaseFragment<FragmentHomeBottomBinding>(R.layout.fragment_home_bottom) {
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var persons: List<MissingPerson>
	private val viewModel: HomeBottomViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()

		viewModel.radius.observe(viewLifecycleOwner) { radiusValue ->
			binding.tvTitle.text = "반경 ${radiusValue}km 이내 실종자 0명"
		}
	}

	private fun setupRecyclerView() {

	}
}
