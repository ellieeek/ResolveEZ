package com.mobile.reconnect.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// 특이사항 Enum
enum class PreSpecialFeature(val label: String) {
    NONE("없음"),
    DISABILITY("장애"),
    DEMENTIA("치매"),
    RUNAWAY("가출인"),
    OTHER("기타")
}

// 성격 Enum
enum class PrePersonality(val label: String) {
    INTROVERT("내성적인"),
    CAUTIOUS("조심스러운"),
    EXTROVERT("외향적인"),
    ACTIVE("활발한")
}

// 성별 Enum
enum class PreGender(val label: String) {
    MALE("남자"),
    FEMALE("여자")
}

// 등록 상태 Enum
enum class PreRegistrationStatus(val label: String) {
    PENDING("등록 대기"),
    APPROVED("심사 완료"),
    COMPLETED("등록 완료")
}

// 사전 등록된 실종자 정보 데이터 클래스
@Parcelize
data class PrePerson(
    val id: Long,
    val name: String,
    val registrationStatus: PreRegistrationStatus,
    val gender: PreGender,
    val birthDate: LocalDate,
    val height: Int?,
    val weight: Int?,
    val imageResId: Int,
    val specialFeature: PreSpecialFeature,
    val personality: PrePersonality?,
    val frequentPlace: String?,
    val additionalInfo: String?,
    val familyImageResId: Int,
    var imageName: String? = null
) : Parcelable {
    // 나이 계산 필드
    val age: Int
        get() = LocalDate.now().year - birthDate.year

    // 생년월일 형식 지정
    val formattedBirthDate: String
        get() = birthDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}