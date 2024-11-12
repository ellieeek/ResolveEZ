package com.mobile.reconnect.data.repository

import com.mobile.reconnect.data.model.search.PageableRequest
import com.mobile.reconnect.data.model.search.SearchRequest
import com.mobile.reconnect.data.model.search.SearchResponse
import com.mobile.reconnect.data.network.api.search.SearchApi
import retrofit2.Call
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
	private val searchApi: SearchApi
) {

	/**
	 * 실종자 검색 API 호출
	 * @param query 검색어
	 * @param pageable 페이지네이션 정보
	 */
	fun searchMissingPerson(query: String, pageable: PageableRequest): Call<SearchResponse> {
		val searchRequest = SearchRequest(query)
		return searchApi.search(searchRequest, pageable)
	}
}
