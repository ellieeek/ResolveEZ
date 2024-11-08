package com.mobile.reconnect

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import com.kakao.vectormap.KakaoMapSdk

class GlobalApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		KakaoSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)
//		KakaoMapSdk.init(this, BuildConfig.KAKAO_MAP_KEY)
	}
}