package com.mobile.reconnect.ui.report.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.R
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
			MissingPerson(1, "이희망", R.drawable.child1, SpecialFeature.DISABILITY, Nationality.DOMESTIC, Gender.MALE, 7, 100, 30, BodyType.AVERAGE, FaceType.OVAL, "흰색 반팔티", "검정 긴바지", "검정 운동화", "하얀 허리띠", "갈색 곱슬머리", LocalDateTime.now(), "부산 동래구"),
			MissingPerson(2, "김영이", R.drawable.senior1, SpecialFeature.DEMENTIA, Nationality.DOMESTIC, Gender.FEMALE, 70, 170, 65, BodyType.SLIM, FaceType.SQUARE, "회색 점퍼", "청바지", "운동화", "안경", "짧은 머리", LocalDateTime.now(), "서울시 종로구")
		)
		_missingPersonList.value = dummyData
	}
}