package com.mobile.reconnect.ui.login

import android.content.Intent
import android.os.Bundle
import com.mobile.reconnect.MainActivity
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.ActivityJoinBinding
import com.mobile.reconnect.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinActivity: BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding.btnJoin.setOnClickListener {
			startActivity(Intent(this, MainActivity::class.java))
			finish()
		}
	}
}