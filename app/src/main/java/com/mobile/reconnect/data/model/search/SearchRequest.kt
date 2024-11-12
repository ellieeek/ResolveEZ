package com.mobile.reconnect.data.model.search
data class SearchRequest(
	val name: String? = null,
	val gender: String? = null,
	val specialFeature: String? = null,
	val age: Int? = null,
	val startDate: String? = null,
	val endDate: String? = null,
	val lastSeenLocation: String? = null
)

data class PageableRequest(
	val page: Int = 0,
	val size: Int = 10,
	val sort: List<String>? = null
)