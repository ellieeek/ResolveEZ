package com.mobile.reconnect.ui.search
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson_ex
import com.mobile.reconnect.databinding.FragmentSearchBinding
import com.mobile.reconnect.ui.search.viewmodel.SearchViewModel
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter

class SearchFragment: BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
	private val viewModel: SearchViewModel by viewModels()

	private lateinit var persons: List<MissingPerson_ex>
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()

		binding.filteringChipAll.setOnClickListener {
			val filterBottomSheet = SearchFilteringFragment()
			filterBottomSheet.show(childFragmentManager, filterBottomSheet.tag)
		}

	}
	private fun setupRecyclerView() {
		persons = listOf(
			MissingPerson_ex(1, "홍길동", "75세, 168cm, 70kg", "치매"),
			MissingPerson_ex(2, "김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson_ex(3, "김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson_ex(4, "홍길동", "75세, 168cm, 70kg", "치매"),
			MissingPerson_ex(5, "김철수", "80세, 175cm, 80kg", "치매"),
			MissingPerson_ex(6, "이영희", "70세, 160cm, 60kg", "치매")
		)

		adapter = MissingPersonsAdapter(persons) { person ->
			Log.d("HomeBottomSheetFragment", "Clicked: ${person.name}")
		}

		binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
		binding.recyclerView.adapter = adapter
	}
}