package com.mobile.reconnect.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentHomeBottomBinding
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter
import com.mobile.reconnect.ui.map.viewmodel.HomeBottomViewModel
import com.mobile.reconnect.ui.map.viewmodel.MapViewModel

class HomeBottomFragment: BaseFragment<FragmentHomeBottomBinding>(R.layout.fragment_home_bottom) {

//	private lateinit var binding: FragmentHomeBottomBinding // XML 바인딩
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var persons: List<MissingPerson>
	private val viewModel: HomeBottomViewModel by viewModels()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
//		binding = FragmentHomeBottomBinding.inflate(inflater, container, false)

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
		persons = listOf(
			MissingPerson("홍길동", "75세, 168cm, 70kg", "치매"),
			MissingPerson("김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson("김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson("홍길동", "75세, 168cm, 70kg", "치매"),
			MissingPerson("김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson("이영희", "70세, 160cm, 60kg", "치매")
		)

		adapter = MissingPersonsAdapter(persons) { person ->
			Log.d("HomeBottomSheetFragment", "Clicked: ${person.name}")
		}

		binding.rvMissingPersonsList.layoutManager = LinearLayoutManager(requireContext())
		binding.rvMissingPersonsList.adapter = adapter
	}
}
