import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.PreGender
import com.mobile.reconnect.data.model.PrePerson
import com.mobile.reconnect.data.model.PrePersonality
import com.mobile.reconnect.data.model.PreRegistrationStatus
import com.mobile.reconnect.data.model.PreSpecialFeature
import java.time.LocalDate

class PrePersonViewModel : ViewModel() {

    private val _prePersons = MutableLiveData<List<PrePerson>>()
    val prePersons: LiveData<List<PrePerson>> get() = _prePersons

    private val _selectedPrePerson = MutableLiveData<PrePerson>()
    val selectedPrePerson: LiveData<PrePerson> get() = _selectedPrePerson

    fun fetchPrePersons() {
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
                registrationStatus = PreRegistrationStatus.COMPLETED, // 등록 완료
                gender = PreGender.FEMALE,
                birthDate = LocalDate.of(1940, 3, 20),
                height = 160,
                weight = 55,
                imageResId = R.drawable.senior1,
                specialFeature = PreSpecialFeature.NONE,
                personality = PrePersonality.ACTIVE,
                frequentPlace = "서울 종로구",
                additionalInfo = "공원을 좋아함",
                familyImageResId = R.drawable.ic_my_paste,
                imageName = "gallery_image.jpg" // 갤러리에서 선택한 이미지
            )
        )
        _prePersons.value = examplePrePersons
    }

    fun selectPrePerson(prePerson: PrePerson) {
        _selectedPrePerson.value = prePerson
    }

    fun updateSelectedImageName(imageName: String) {
        _selectedPrePerson.value = _selectedPrePerson.value?.copy(imageName = imageName)
    }

    fun clearSelectedImageName() {
        _selectedPrePerson.value = _selectedPrePerson.value?.copy(imageName = null)
    }
}