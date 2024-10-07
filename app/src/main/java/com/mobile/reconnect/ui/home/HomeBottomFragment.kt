package com.mobile.reconnect.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.Nullable
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentHomeBinding
import com.mobile.reconnect.databinding.FragmentHomeBottomBinding
import com.mobile.reconnect.ui.home.adapter.MissingPersonsAdapter
import com.mobile.reconnect.ui.home.viewmodel.HomeBottomViewModel
import com.mobile.reconnect.ui.home.viewmodel.HomeViewModel
import com.software.somding.presentation.common.BaseFragment

class HomeBottomFragment: BaseFragment<FragmentHomeBottomBinding>(R.layout.fragment_home_bottom) {

	// bottomSheetBehavior
	private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var persons: List<MissingPerson>

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

	}

	override fun onResume() {
		super.onResume()
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		// RecyclerView 초기화
		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		persons = listOf(
			MissingPerson("홍길동", "75세, 168cm, 70kg", "치매"),
			MissingPerson("김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson("이영희", "70세, 160cm, 60kg", "치매")
		)

		adapter = MissingPersonsAdapter(persons) { person ->
			Log.d("HomeBottomFragment", "Clicked: ${person.name}")
		}

		binding.rvMissingPersonsList.layoutManager = LinearLayoutManager(requireContext())
		binding.rvMissingPersonsList.adapter = adapter
	}
}
