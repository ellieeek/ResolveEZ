package com.mobile.reconnect.utils

import com.mobile.reconnect.BuildConfig

object Constants {
    const val TAG = "TAG"
    const val APP_KEY = "YOUR NATIVE_APP_KEY"
	const val BASE_URL = BuildConfig.BASE_URL

	val MESSAGE_TYPE_ENTER: String = "ENTER"
	var SENDER: String = "DEFAULT"
	val URL: String = "ws://223.130.139.166:8080/ws"

	fun set(sender: String){
		SENDER = sender
	}
}