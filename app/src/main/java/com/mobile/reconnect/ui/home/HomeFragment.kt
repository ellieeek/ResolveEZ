package com.mobile.reconnect.ui.home

import android.Manifest
import android.content.pm.PackageManager
import android.location.Address
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentHomeBinding
import com.mobile.reconnect.ui.home.viewmodel.HomeViewModel
import com.software.somding.presentation.common.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnMapReadyCallback {
	private val viewModel: HomeViewModel by viewModels()
	private lateinit var map: GoogleMap
	private lateinit var fusedLocationClient: FusedLocationProviderClient

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())

		val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
		mapFragment.getMapAsync(this)
	}

	override fun onMapReady(googleMap: GoogleMap) {
		map = googleMap

		if (ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_FINE_LOCATION
			) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_COARSE_LOCATION
			) != PackageManager.PERMISSION_GRANTED
		) {
			ActivityCompat.requestPermissions(
				requireActivity(),
				arrayOf(
					Manifest.permission.ACCESS_FINE_LOCATION,
					Manifest.permission.ACCESS_COARSE_LOCATION
				),
				LOCATION_PERMISSION_REQUEST_CODE
			)
			return
		}

		map.isMyLocationEnabled = true
		map.uiSettings.isZoomControlsEnabled = true

		fusedLocationClient.lastLocation.addOnSuccessListener { location ->
			location?.let {
				val currentLatLng = LatLng(it.latitude, it.longitude)
				map.addMarker(MarkerOptions().position(currentLatLng).title("현재 위치"))
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
			}
		}
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
	}
}
