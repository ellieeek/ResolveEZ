/*
package com.mobile.reconnect

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.mobile.reconnect.databinding.ActivityLoginBinding
import com.mobile.reconnect.utils.Constants
import com.software.somding.presentation.common.BaseActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login), View.OnClickListener {

	private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
		if (error != null) {
			Log.e(Constants.TAG, "로그인 실패 $error")
		} else if (token != null) {
			Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
			nextMainActivity()
		}
	}

	override fun onClick(v: View?) {
		when (v?.id) {
			binding.btnLogin.id -> {
				if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
					UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
						if (error != null) {
							Log.e(Constants.TAG, "로그인 실패 $error")
							if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
								return@loginWithKakaoTalk
							} else {
								UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
							}
						} else if (token != null) {
							Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
							Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
							nextMainActivity()
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

		KakaoSdk.init(this, Constants.APP_KEY)
		if (AuthApiClient.instance.hasToken()) {
			UserApiClient.instance.accessTokenInfo { _, error ->
				if (error == null) {
					nextMainActivity()
				}
			}
		}

		setContentView(binding.root)

		binding.btnKakaoLogin.setOnClickListener(this)
	}

	private fun nextMainActivity() {
		startActivity(Intent(this, MainActivity::class.java))
		finish()
	}
}*/
