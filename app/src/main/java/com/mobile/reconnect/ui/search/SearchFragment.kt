package com.mobile.reconnect.ui.search
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentSearchBinding
import com.mobile.reconnect.ui.search.viewmodel.SearchViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class SearchFragment: BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {
	private val viewModel: SearchViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.text.observe(viewLifecycleOwner) {
			binding.textSearch.text = it
		}
	}

}