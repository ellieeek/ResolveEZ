package com.mobile.reconnect.data.network.api.search

import com.mobile.reconnect.data.model.search.PageableRequest
import com.mobile.reconnect.data.model.search.SearchRequest
import com.mobile.reconnect.data.model.search.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
	@GET("/api/search")
	fun search(
		@Query("searchRequest") searchRequest: SearchRequest,
		@Query("pageable") pageable: PageableRequest
	): Call<SearchResponse>
}
