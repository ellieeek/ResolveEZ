package com.mobile.reconnect

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mobile.reconnect.databinding.ActivityMainBinding
import com.mobile.reconnect.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
		val navController = navHostFragment.navController

		binding.navView.setupWithNavController(navController)
	}
}