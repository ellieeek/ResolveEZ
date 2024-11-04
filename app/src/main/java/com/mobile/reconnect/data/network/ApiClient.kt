package com.mobile.reconnect.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.CookieManager

object ApiClient {
	private const val BASE_URL = ""
//	private const val BASE_URL = BuildConfig.BASE_URL
val cookieManager = CookieManager()

	private var builder = OkHttpClient().newBuilder()
	private var okHttpClient = builder
//		.cookieJar(JavaNetCookieJar(CookieManager()))
		.build()

	internal val retrofit: Retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	internal inline fun <reified T> createService(): T {
		return retrofit.create(T::class.java)
	}

}