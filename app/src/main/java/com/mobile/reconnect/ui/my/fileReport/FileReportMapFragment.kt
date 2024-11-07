package com.mobile.reconnect.ui.my.fileReport

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentFileReport2Binding
import com.mobile.reconnect.databinding.FragmentFileReportMapBinding
import com.mobile.reconnect.ui.my.viewmodel.MyViewModel
import com.mobile.reconnect.ui.common.BaseFragment

class FileReportMapFragment :
	BaseFragment<FragmentFileReportMapBinding>(R.layout.fragment_file_report_map) {
	private val viewModel: MyViewModel by viewModels()
	private val mapView: MapView = binding.mapView

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		mapView.start(object : MapLifeCycleCallback() {
			override fun onMapDestroy() {
				// 지도 API 가 정상적으로 종료될 때 호출됨
			}

			override fun onMapError(error: Exception) {
				// 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됨
			}
		}, object : KakaoMapReadyCallback() {
			override fun onMapReady(kakaoMap: KakaoMap) {
				// 인증 후 API 가 정상적으로 실행될 때 호출됨
			}
		})

	}

	override fun onPause() {
		super.onPause()
		mapView.pause()
	}

	override fun onResume() {
		super.onResume()
		mapView.resume()
	}

}