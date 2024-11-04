package com.mobile.reconnect.ui.map.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeBottomViewModel : ViewModel() {
	private val _radius = MutableLiveData<Int>()
	val radius: LiveData<Int> get() = _radius

	fun updateRadius(radiusValue: Int) {
		_radius.value = radiusValue
		Log.d("HomeBottomViewModel", "Radius updated: $radiusValue")
	}
}