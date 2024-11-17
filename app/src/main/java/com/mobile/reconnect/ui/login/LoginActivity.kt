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
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login), View.OnClickListener {

	// 로그인 콜백
	private val mCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
		if (error != null) {
			Log.e(Constants.TAG, "로그인 실패 $error")
			Toast.makeText(this, "로그인 실패. 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
		} else if (token != null) {
			Log.d(Constants.TAG, "로그인 성공 ${token.accessToken}")
			Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
			nextActivity()
		}
	}

	// 로그인 버튼 클릭 리스너
	override fun onClick(v: View?) {
		when (v?.id) {
			binding.btnKakaoLogin.id -> {
				// 카카오톡 로그인 가능 여부 확인
				if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
					UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
						if (error != null) {
							Log.e(Constants.TAG, "카카오톡 로그인 실패 $error")
							// 카카오톡 로그인 실패 시 계정으로 전환
							if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
								return@loginWithKakaoTalk
							} else {
								UserApiClient.instance.loginWithKakaoAccount(
									this,
									callback = mCallback
								)
							}
						} else {
							Log.d(Constants.TAG, "카카오톡 로그인 성공 ${token?.accessToken}")
							Toast.makeText(this, "카카오톡 로그인 성공!", Toast.LENGTH_SHORT).show()
							nextActivity()
						}
					}
				} else {
					UserApiClient.instance.loginWithKakaoAccount(this, callback = mCallback)
				}
			}
		}
	}

	// 액티비티 생성 시 초기화
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		// 카카오 SDK 초기화
		Log.d(Constants.TAG, "keyhash : ${Utility.getKeyHash(this)}")
		KakaoSdk.init(this, BuildConfig.KAKAO_LOGIN_KEY)

		// 이미 로그인된 상태인지 확인
		if (AuthApiClient.instance.hasToken()) {
			// 로그인된 상태라면 액세스 토큰 유효성 검사
			UserApiClient.instance.accessTokenInfo { _, error ->
				if (error == null) {
					nextActivity() // 토큰 유효하면 바로 JoinActivity로 이동
				} else {
					Log.e(Constants.TAG, "토큰 정보 확인 실패 $error")
				}
			}
		}

		// 로그인 버튼 클릭 리스너 설정
		binding.btnKakaoLogin.setOnClickListener(this)

		binding.btnNaverLogin.setOnClickListener {
			startActivity(Intent(this, MainActivity::class.java))
			finish()
		}
	}

	// 로그인 후 다음 액티비티로 이동
	private fun nextActivity() {
		startActivity(Intent(this, JoinActivity::class.java))
		finish() // 로그인 후 현재 액티비티 종료
	}
}
