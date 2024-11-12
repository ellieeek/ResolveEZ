package com.mobile.reconnect

import android.app.Application
import android.util.Log
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.kakao.vectormap.KakaoMapSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GlobalApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		KakaoSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)
		KakaoMapSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)

		var keyHash = Utility.getKeyHash(this)
		Log.i("GlobalApplication", "$keyHash")
	}
}