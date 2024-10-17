package com.mobile.reconnect

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
	override fun onCreate() {
		super.onCreate()

		KakaoSdk.init(this, "11f925ca83f2b88cd5053b2ea7c08b77")
	}
}