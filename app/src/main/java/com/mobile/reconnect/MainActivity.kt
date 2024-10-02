package com.mobile.reconnect

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.mobile.reconnect.databinding.ActivityMainBinding
import com.software.somding.presentation.common.BaseActivity

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		val navView: BottomNavigationView = binding.navView

		val navHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
		val navController = navHostFragment.navController

		val appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.navigation_home,
				R.id.navigation_report,
				R.id.navigation_search,
				R.id.navigation_mypage
			)
		)
//		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
	}
}