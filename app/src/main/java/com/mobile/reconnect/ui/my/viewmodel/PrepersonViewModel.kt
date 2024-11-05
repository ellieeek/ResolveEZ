package com.mobile.reconnect.ui.my.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.PrePerson
import com.mobile.reconnect.data.model.PreGender
import com.mobile.reconnect.data.model.PrePersonality
import com.mobile.reconnect.data.model.PreRegistrationStatus
import com.mobile.reconnect.data.model.PreSpecialFeature
import java.time.LocalDate

class PrePersonViewModel : ViewModel() {

    private val _prePersons = MutableLiveData<List<PrePerson>>()
    val prePersons: LiveData<List<PrePerson>> get() = _prePersons

    fun fetchPrePersons() {
        // 예시 데이터
        val examplePrePersons = listOf(
            PrePerson(
                id = 1,
                name = "이현수",
                registrationStatus = PreRegistrationStatus.APPROVED,
                gender = PreGender.MALE,
                birthDate = LocalDate.of(2015, 5, 15),
                height = 105,
                weight = 30,
                imageResId = R.drawable.child1,
                specialFeature = PreSpecialFeature.DISABILITY,
                personality = PrePersonality.INTROVERT,
                frequentPlace = "서울 강남구",
                additionalInfo = "겁이 많음",
                familyImageResId = R.drawable.ic_my_paste
            ),
            PrePerson(
                id = 2,
                name = "김순자",
                registrationStatus = PreRegistrationStatus.COMPLETED,
                gender = PreGender.MALE,
                birthDate = LocalDate.of(1940, 3, 20),
                height = 180,
                weight = 80,
                imageResId = R.drawable.senior1,
                specialFeature = PreSpecialFeature.NONE,
                personality = PrePersonality.ACTIVE,
                frequentPlace = "서울 종로구",
                additionalInfo = "공원을 좋아함",
                familyImageResId = R.drawable.ic_my_paste
            )
        )
        _prePersons.value = examplePrePersons
    }
}