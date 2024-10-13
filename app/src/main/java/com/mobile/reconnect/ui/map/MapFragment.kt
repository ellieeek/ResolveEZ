package com.mobile.reconnect.ui.map

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMapBinding
import com.mobile.reconnect.ui.map.viewmodel.MapViewModel
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.map.viewmodel.HomeBottomViewModel

class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
	private val viewModel: HomeBottomViewModel by activityViewModels()

	private lateinit var map: GoogleMap
	private lateinit var fusedLocationClient: FusedLocationProviderClient

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
	private var currentCircle: Circle? = null


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

		val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
		mapFragment.getMapAsync(this)

		setBottomSheet()

		// Chip 클릭 리스너 설정
		binding.radius1km.setOnClickListener {
			drawCircle(1000.0, Color.parseColor("#F89035"))
			viewModel.updateRadius(1)
		}

		binding.radius2km.setOnClickListener {
			drawCircle(2000.0, Color.parseColor("#7EB9FD"))
			viewModel.updateRadius(2)
		}

		binding.radius3km.setOnClickListener {
			drawCircle(3000.0, Color.parseColor("#89D38B"))
			viewModel.updateRadius(3)
		}

		binding.radius4km.setOnClickListener {
			drawCircle(4000.0, Color.parseColor("#E4BDFF"))
			viewModel.updateRadius(4)
		}

		binding.radius5km.setOnClickListener {
			drawCircle(5000.0, Color.parseColor("#FDF77B"))
			viewModel.updateRadius(5)
		}

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
				Log.d("현재 위치", "${currentLatLng.latitude}/${currentLatLng.longitude}")
				map.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
			}
		}

		drawCircle(1000.0, Color.parseColor("#7EB9FD"))
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
	}

	private fun drawCircle(radius: Double, color: Int) {
		// 기존 원 삭제
		currentCircle?.remove()

		if (ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_FINE_LOCATION
			) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
				requireContext(),
				Manifest.permission.ACCESS_COARSE_LOCATION
			) != PackageManager.PERMISSION_GRANTED
		) {
			return
		}
		fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
			if (location != null) {
				val currentLatLng = LatLng(location.latitude, location.longitude)

				// 새로운 원 그리기
				currentCircle = map.addCircle(
					CircleOptions()
						.center(currentLatLng)
						.radius(radius)
						.strokeColor(color)
						.fillColor(adjustAlpha(color, 0.3f))
						.strokeWidth(2f)
				)
			}
		}
	}

	private fun adjustAlpha(color: Int, factor: Float): Int {
		val alpha = Math.round(Color.alpha(color) * factor)
		val red = Color.red(color)
		val green = Color.green(color)
		val blue = Color.blue(color)
		return Color.argb(alpha, red, green, blue)
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
