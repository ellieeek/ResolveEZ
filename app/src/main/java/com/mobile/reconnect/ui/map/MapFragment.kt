package com.mobile.reconnect.ui.map

import android.Manifest
import android.R.attr.label
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Location
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MediatorLiveData
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson_ex
import com.mobile.reconnect.databinding.FragmentMapBinding
import com.mobile.reconnect.ui.common.BaseFragment
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter
import com.mobile.reconnect.ui.map.viewmodel.HomeBottomViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import okhttp3.OkHttpClient
import org.json.JSONObject
import ua.naiksoftware.stomp.Stomp
import ua.naiksoftware.stomp.StompClient
import ua.naiksoftware.stomp.dto.LifecycleEvent
import ua.naiksoftware.stomp.dto.StompHeader


/*
class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map), OnMapReadyCallback {
	private val viewModel: HomeBottomViewModel by activityViewModels()

	private lateinit var map: GoogleMap
	private lateinit var fusedLocationClient: FusedLocationProviderClient

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
	private var currentCircle: Circle? = null
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var persons: List<MissingPerson_ex>


	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
		KakaoMapSdk.init(requireContext(), BuildConfig.KAKAO_LOGIN_KEY)

		val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
		mapFragment.getMapAsync(this)

		setBottomSheet()
		setupRecyclerView()

		// Chip 클릭 리스너 설정
		binding.radius1km.setOnClickListener {
			drawCircle(1000.0, Color.parseColor("#F89035"))
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			viewModel.updateRadius(1)
		}

		binding.radius2km.setOnClickListener {
			drawCircle(2000.0, Color.parseColor("#7EB9FD"))
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			viewModel.updateRadius(2)
		}

		binding.radius3km.setOnClickListener {
			drawCircle(3000.0, Color.parseColor("#89D38B"))
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			viewModel.updateRadius(3)
		}

		binding.radius4km.setOnClickListener {
			drawCircle(4000.0, Color.parseColor("#E4BDFF"))
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			viewModel.updateRadius(4)
		}

		binding.radius5km.setOnClickListener {
			drawCircle(5000.0, Color.parseColor("#FDF77B"))
			bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			viewModel.updateRadius(5)
		}

		viewModel.radius.observe(viewLifecycleOwner) { radiusValue ->
			binding.tvTitle.text = "반경 ${radiusValue}km 이내 실종자 0명"
		}
	}

	*/
/*	@SuppressLint("MissingPermission")
	private fun drawCircle(radius: Double, color: Int) {
		// 기존 원 삭제
		currentCircle?.remove()

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

	*/

@AndroidEntryPoint
class MapFragment: BaseFragment<FragmentMapBinding>(R.layout.fragment_map) {
	private val viewModel: HomeBottomViewModel by activityViewModels()

	private lateinit var mapView: MapView
	private var kakaoMap: KakaoMap? = null
	private lateinit var fusedLocationClient: FusedLocationProviderClient

	private lateinit var mapViewContainer: ViewGroup

	private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>
	private var currentCircle: Circle? = null
	private lateinit var adapter: MissingPersonsAdapter
	private lateinit var persons: List<MissingPerson_ex>

	private lateinit var client: OkHttpClient
	private lateinit var stompClient: StompClient
	private lateinit var topic: Disposable
	private lateinit var jsonObject: JSONObject
	private var latitude: Double = 0.0
	private var longitude: Double = 0.0

	private val PERMISSIONS_REQUEST_CODE = 100
	private val permissions = arrayOf(
		Manifest.permission.ACCESS_FINE_LOCATION,
		Manifest.permission.ACCESS_COARSE_LOCATION
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())

		setBottomSheet() // 바텀시트 설정
		showMapView() // 카카오 맵 설정
		checkPermissions() // 위치 권한 설정
		setupStompConnection() // stomp 설정

		viewModel.radius.observe(viewLifecycleOwner) { radiusValue ->
			binding.tvTitle.text = "반경 ${radiusValue}km 이내 실종자 0명"
		}

		client = OkHttpClient()
		val url = "ws://223.130.139.166:8080/ws"
		stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)
		runStomp()
	}

	/***
	 * 카카오 맵
	 */
	private fun showMapView() {
		mapView = binding.map

		// KakaoMapSDK initialization
		mapView.start(object : MapLifeCycleCallback() {
			override fun onMapDestroy() {
				Log.d("KakaoMap", "onMapDestroy")
			}

			override fun onMapError(p0: Exception?) {
				Log.e("KakaoMap", "onMapError")
			}
		}, object : KakaoMapReadyCallback() {
			override fun onMapReady(kakaomap: KakaoMap) {
				kakaoMap = kakaomap
			}
		})
	}

	/***
	 * bottom sheet
	 */
	private fun setBottomSheet() {
		val bottomSheet = view?.findViewById<View>(R.id.bottom_sheet)
		bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet!!)
		bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN

		bottomSheet.setOnClickListener {
			if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_HALF_EXPANDED) {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
			} else {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
			}
		}
	}

	/***
	 * 위치 권한
	 */
	private fun checkPermissions() {
		val requestList = mutableListOf<String>()
		for (permission in permissions) {
			if (ActivityCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
				requestList.add(permission)
				Log.d("엥", "ㅜㅜㅜ")
			}
		}

		if (requestList.isNotEmpty()) {
			// 권한이 없으면 요청
			ActivityCompat.requestPermissions(requireActivity(), requestList.toTypedArray(), PERMISSIONS_REQUEST_CODE)
		} else {
			// 권한이 있으면 바로 위치 가져오기
			getLocation()
			Log.d("엥", "getLocation()")
		}
	}

	override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults)
		if (requestCode == PERMISSIONS_REQUEST_CODE) {
			for (grantResult in grantResults) {
				if (grantResult == PackageManager.PERMISSION_DENIED) {
					Toast.makeText(requireContext(), "위치 권한이 거부되었습니다.", Toast.LENGTH_SHORT).show()
					return
				}
			}
		}
	}

	@SuppressLint("MissingPermission")
	private fun getLocation() {
		// 위치 서비스가 활성화되어 있는지 확인
		val locationRequest = LocationRequest.create().apply {
			interval = 10000 // 10초마다 위치 업데이트
			fastestInterval = 5000 // 5초마다 빠른 위치 업데이트
			priority = LocationRequest.PRIORITY_HIGH_ACCURACY
		}

		val locationCallback = object : LocationCallback() {
			override fun onLocationResult(p0: LocationResult) {
				if (p0 != null) {
					super.onLocationResult(p0)
				}
				p0?.let { it ->
					val location = it.lastLocation
					location?.let {
						viewModel.setLocation(location.latitude, location.longitude)

//						drawCircle(location.latitude, location.longitude) // 위치를 기반으로 원 그리기
					}
				}
			}
		}

		if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
			fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper())
		} else {
			// 권한이 없으면 권한 요청
			ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_CODE)
		}
	}

	/***
	 * stomp
	 */
	private fun setupStompConnection() {
		client = OkHttpClient()
		val url = "ws://223.130.139.166:8080/ws"
		stompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, url)

		runStomp()
	}

	/***
	 * stomp
	 */
	private fun runStomp() {
		stompClient.topic("/topic/predicted-locations").subscribe { topicMessage ->
			Log.i("Message Received", topicMessage.payload)
		}

		val headerList = arrayListOf<StompHeader>()
		stompClient.connect(headerList)

		stompClient.lifecycle().subscribe { lifecycleEvent ->
			when (lifecycleEvent.type) {
				LifecycleEvent.Type.OPENED -> {
					Log.i("OPENED", "STOMP connection opened")
				}

				LifecycleEvent.Type.CLOSED -> {
					Log.i("CLOSED", "STOMP connection closed")
				}

				LifecycleEvent.Type.ERROR -> {
					Log.i("ERROR", "Error in STOMP connection")
					Log.e("CONNECT ERROR", lifecycleEvent.exception.toString())
				}

				else -> {
					Log.i("ELSE", lifecycleEvent.message ?: "Unknown event")
				}
			}
		}

		val combinedLocation = MediatorLiveData<Pair<Double, Double>>()

		combinedLocation.addSource(viewModel.uLatitude) { latitude ->
			val longitude = viewModel.uLongitude.value ?: 0.0
			combinedLocation.value = Pair(latitude, longitude)
		}

		combinedLocation.addSource(viewModel.uLongitude) { longitude ->
			val latitude = viewModel.uLatitude.value ?: 0.0
			combinedLocation.value = Pair(latitude, longitude)
		}

		combinedLocation.observe(viewLifecycleOwner) { (latitude, longitude) ->
			Log.d("내 위치...", "위도: $latitude, 경도: $longitude")

			val data = JSONObject().apply {
				put("userLocation", JSONObject().apply {
					put("latitude", latitude)
					put("longitude", longitude)
				})
			}

			stompClient.send("/app/ws/location", data.toString()).subscribe()
		}
	}

	companion object {
		private const val LOCATION_PERMISSION_REQUEST_CODE = 1001
	}

	@SuppressLint("MissingPermission")
	private fun drawCircle(radius: Double, color: Int) {
		// 기존 원 삭제
		currentCircle?.remove()

		fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
			if (location != null) {
				val currentLatLng = LatLng(location.latitude, location.longitude)

				// 새로운 원 그리기
//				currentCircle = kakaoMap.addCircle(
//					CircleOptions()
//						.center(currentLatLng)
//						.radius(radius)
//						.strokeColor(color)
//						.fillColor(adjustAlpha(color, 0.3f))
//						.strokeWidth(2f)
//				)
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

}