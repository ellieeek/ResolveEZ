package com.mobile.reconnect.data.model

import com.mobile.reconnect.data.model.enumeration.Status

data class MyReportList (
	val name: String,
	val status: Status,
	val date: String
)