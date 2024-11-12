package com.mobile.reconnect.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.kakao.sdk.auth.AuthApiClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import com.mobile.reconnect.BuildConfig
import com.mobile.reconnect.MainActivity
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.ActivityLoginBinding
import com.mobile.reconnect.utils.Constants
import com.mobile.reconnect.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login), View.OnClickListener {

	private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
		if (error != null) {
			Log.e(Constants.TAG, "로그인 실패 $error")
		} else if (token != null) {
			Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")

			nextActivity()
		}
	}

	override fun onClick(v: View?) {
		when (v?.id) {
			binding.btnKakaoLogin.id -> {
				if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
					UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
						if (error != null) {
							Log.e(Constants.TAG, "로그인 실패 $error")
							if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
								return@loginWithKakaoTalk
							} else {
								UserApiClient.instance.loginWithKakaoAccount(
									this,
									callback = mCallback
								)
							}
						} else if (token != null) {
							Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
							Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
							nextActivity()
						}
					}
				} else {
					UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
				}
			}
		}
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		Log.d(Constants.TAG, "keyhash : ${Utility.getKeyHash(this)}")

		KakaoSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)
		if (AuthApiClient.instance.hasToken()) {
			UserApiClient.instance.accessTokenInfo { _, error ->
				if (error == null) {
					nextActivity()
				}
			}
		}
		binding.btnKakaoLogin.setOnClickListener(this)
		binding.btnNaverLogin.setOnClickListener{
			startActivity(Intent(this, MainActivity::class.java))
			finish()
		}
	}

	private fun nextActivity() {
		startActivity(Intent(this, JoinActivity::class.java))
		finish()
	}
}