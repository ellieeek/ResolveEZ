package com.mobile.reconnect.ui.my.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.data.model.MyNotificationList

class NotificationViewModel : ViewModel() {

    private val _alarmList = MutableLiveData<List<MyNotificationList>>()
    val notificationList: LiveData<List<MyNotificationList>> get() = _alarmList

    fun fetchAlarms() {
        val exampleNotifications = listOf(
            MyNotificationList(1, "실종", "실종자 홍길동씨가 나와 반경 2km 이내에 있을 수 있습니다", "남, 75세, 168cm, 70kg, 치매"),
            MyNotificationList(2, "발견", "실종자 홍길동씨가 발견되었습니다", ""),
            MyNotificationList(3, "실종", "실종자 홍길동씨가 나와 반경 2km 이내에 있을 수 있습니다", "남, 75세, 168cm, 70kg, 치매")
        )
        _alarmList.value = exampleNotifications
    }
}
