package com.mobile.reconnect.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobile.reconnect.data.model.search.MissingPerson
import com.mobile.reconnect.data.model.search.PageableRequest
import com.mobile.reconnect.data.model.search.SearchResponse
import com.mobile.reconnect.data.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val searchRepository: SearchRepository
) : ViewModel() {

	private val _missingPersons = MutableLiveData<
			List<MissingPerson>?>()
	val missingPersons: MutableLiveData<
			List<MissingPerson>?>
		get() = _missingPersons

	private val _error = MutableLiveData<String>()
	val error: LiveData<String> get() = _error

	private var currentPage = 0

	// 실종자 검색 함수
	fun searchMissingPersons(query: String) {
		val pageable = PageableRequest(currentPage, 10) // 10개의 항목을 한 페이지로 설정
		Log.d("SearchViewModel", "Searching for: $query with page: $currentPage")

		searchRepository.searchMissingPerson(query, pageable).enqueue(object :
			Callback<SearchResponse> {
			override fun onResponse(
				call: Call<SearchResponse>,
				response: Response<SearchResponse>
			) {
				if (response.isSuccessful) {
					// 성공적인 응답 처리
					val responseBody = response.body()
					if (responseBody != null && responseBody.content.isNotEmpty()) {
						_missingPersons.value = responseBody.content
						currentPage++
						Log.d(
							"SearchViewModel",
							"Response successful: ${responseBody.content.size} items found"
						)
					} else {
						Log.d("SearchViewModel", "No data found")
					}
				} else {
					// 실패 시 처리
					Log.e(
						"SearchViewModel",
						"Error response: ${response.code()} - ${response.message()} - ${response.body()}"
					)
					_error.value = "검색 결과를 가져오지 못했습니다."
				}
			}

			override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
				// 네트워크 오류 처리
				Log.e("SearchViewModel", "Network error: ${t.localizedMessage}")
				_error.value = "네트워크 오류가 발생했습니다."
			}
		})
	}

	// 필터링 처리 로직 추가
	fun applyFilters(filters: Map<String, String>) {
		// 필터링 로직을 추가할 수 있습니다.
		// 예: 나이, 성별 등 필터 조건을 적용하여 서버 요청
		Log.d("SearchViewModel", "Applying filters: $filters")
	}
}
