package com.mobile.reconnect.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.mobile.reconnect.MainActivity
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.ActivityJoinBinding
import com.mobile.reconnect.databinding.ActivityLoginBinding
import com.software.somding.presentation.common.BaseActivity

class JoinActivity: BaseActivity<ActivityJoinBinding>(R.layout.activity_join) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding.btnJoin.setOnClickListener {
			startActivity(Intent(this, MainActivity::class.java))
			finish()
		}
	}
}