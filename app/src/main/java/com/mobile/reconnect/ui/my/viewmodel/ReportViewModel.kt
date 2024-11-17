package com.mobile.reconnect.ui.my.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.data.model.MyReportList
import com.mobile.reconnect.data.model.enumeration.Status

class ReportViewModel : ViewModel() {

    private val _reports = MutableLiveData<List<MyReportList>>()
    val reports: LiveData<List<MyReportList>> get() = _reports

    fun fetchReports() {
        val exampleReports = listOf(
	        MyReportList("홍길동", Status.SEARCHING, "2024. 09. 10"),
	        MyReportList("이몽룡", Status.FIND, "2024. 09. 11"),
	        MyReportList("성춘향", Status.SEARCHING, "2024. 09. 12")
        )
        _reports.value = exampleReports
    }
}
