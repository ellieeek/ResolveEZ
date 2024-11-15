package com.mobile.reconnect.ui.map.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeBottomViewModel : ViewModel() {
	private val _radius = MutableLiveData<Int>()
	val radius: LiveData<Int> get() = _radius

	private val _uLatitude = MutableLiveData<Double>()
	val uLatitude: LiveData<Double> get() = _uLatitude

	private val _uLongitude = MutableLiveData<Double>()
	val uLongitude: LiveData<Double> get() = _uLongitude

	fun updateRadius(radiusValue: Int) {
		_radius.value = radiusValue
	}

	fun setLocation(latitude: Double, longitude: Double) {
		_uLatitude.value = latitude
		_uLongitude.value = longitude
	}
}