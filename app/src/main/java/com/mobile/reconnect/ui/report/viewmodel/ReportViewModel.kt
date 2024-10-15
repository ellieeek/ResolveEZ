package com.mobile.reconnect.ui.report.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.data.model.BodyType
import com.mobile.reconnect.data.model.FaceType
import com.mobile.reconnect.data.model.Gender
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.data.model.Nationality
import com.mobile.reconnect.data.model.SpecialFeature
import java.time.LocalDateTime

class ReportViewModel : ViewModel() {

	// 실종자 목록을 관리하는 LiveData
	private val _missingPersonList = MutableLiveData<List<MissingPerson>>()
	val missingPersonList: LiveData<List<MissingPerson>> get() = _missingPersonList

	// 실종자 목록을 로드하는 메서드
	fun loadMissingPersons() {
		// 여기에 실제 데이터를 불러오는 로직을 추가 (서버 API 호출 또는 로컬 데이터베이스)
		val dummyData = listOf(
			MissingPerson(1, "홍길동", "https://example.com/image.jpg", SpecialFeature.NONE, Nationality.DOMESTIC, Gender.MALE, 25, 180, 75, BodyType.AVERAGE, FaceType.OVAL, "청색 셔츠", "검은색 바지", "운동화", "시계", "짧은 머리", LocalDateTime.now(), "서울시 강남구"),
			MissingPerson(2, "이순신", "https://example.com/image2.jpg", SpecialFeature.DEMENTIA, Nationality.DOMESTIC, Gender.MALE, 70, 170, 65, BodyType.SLIM, FaceType.SQUARE, "회색 점퍼", "청바지", "운동화", "안경", "짧은 머리", LocalDateTime.now(), "서울시 종로구")
		)
		_missingPersonList.value = dummyData
	}
}