package com.mobile.reconnect.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentHomeBinding
import com.mobile.reconnect.ui.map.viewmodel.MapViewModel
import com.software.somding.presentation.common.BaseFragment

class MapFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), OnMapReadyCallback {
	private val viewModel: MapViewModel by viewModels()

	private lateinit var map: GoogleMap
	private lateinit var fusedLocationClient: FusedLocationProviderClient

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

		val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
		mapFragment.getMapAsync(this)

		setBottomSheet()
	}

	/***
	 * google map
	 */
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
//				map.addMarker(MarkerOptions().position(currentLatLng).title("현재 위치"))
				Log.d("현재 위치", "${currentLatLng.latitude}/${currentLatLng.longitude}")
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
			}
		}
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
	}


	/***
	 * bottom sheet
	 */
	private fun setBottomSheet() {
		val bottomSheet = view?.findViewById<View>(R.id.bottom_sheet)
		bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)

		bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

		bottomSheet.setOnClickListener {
			if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
			} else {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			}
		}
	}
}
