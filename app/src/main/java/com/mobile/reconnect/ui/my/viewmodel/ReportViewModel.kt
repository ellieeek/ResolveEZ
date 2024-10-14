package com.mobile.reconnect.ui.my.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.data.model.MyReportList

class ReportViewModel : ViewModel() {

    private val _reports = MutableLiveData<List<MyReportList>>()
    val reports: LiveData<List<MyReportList>> get() = _reports

    fun fetchReports() {
        val exampleReports = listOf(
	        MyReportList("홍길동", "수색 중", "2024. 09. 10"),
	        MyReportList("이몽룡", "발견 완료", "2024. 09. 11"),
	        MyReportList("성춘향", "수색 중", "2024. 09. 12")
        )
        _reports.value = exampleReports
    }
}
