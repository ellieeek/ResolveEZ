package com.mobile.reconnect.ui.report

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentReportRegistrationBinding
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.mobile.reconnect.data.model.AgeGroup
import com.mobile.reconnect.data.model.ReportGender
import com.mobile.reconnect.data.model.ReportRequest
import com.mobile.reconnect.data.model.ReportSpecialFeature
import com.mobile.reconnect.ui.report.viewmodel.ReportRegistrationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReportRegistrationFragment : Fragment() {

    private val viewModel: ReportRegistrationViewModel by viewModels()
    private var _binding: FragmentReportRegistrationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 MissingPerson ID
        val missingPersonId = arguments?.getLong("missingPersonId") ?: return

        // RadioGroup 성별 선택 시 이벤트 처리
        binding.genderRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            val selectedGender: ReportGender? = when (checkedId) {
                R.id.radioButtonMale -> ReportGender.MALE
                R.id.radioButtonFemale -> ReportGender.FEMALE
                else -> null
            }
            selectedGender?.let { gender ->
                viewModel.updateGender(
                    missingPersonId = missingPersonId,
                    gender = gender,
                    onSuccess = {
                        Toast.makeText(context, "성별이 성공적으로 업데이트되었습니다.", Toast.LENGTH_SHORT).show()
                    },
                    onError = { errorMessage ->
                        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                    }
                )
            }
        }

        // 제출 버튼 클릭
        binding.reportbtn.setOnClickListener {
            val reportRequest = collectReportData(missingPersonId)
            if (reportRequest != null) {
                viewModel.submitReport(reportRequest, ::onReportSuccess, ::onReportError)
            } else {
                Toast.makeText(context, "모든 필수 항목을 입력해주세요.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun collectReportData(missingPersonId: Long): ReportRequest? {
        val gender = when (binding.genderRadioGroup.checkedRadioButtonId) {
            R.id.radioButtonMale -> ReportGender.MALE
            R.id.radioButtonFemale -> ReportGender.FEMALE
            else -> null
        }
        val locationFound = binding.editTextText.text.toString()
        val foundLatitude = 37.5665 // GPS에서 가져오기
        val foundLongitude = 126.9780 // GPS에서 가져오기
        val additionalDescription = binding.editTextText5.text.toString()

        return if (gender != null && locationFound.isNotEmpty()) {
            ReportRequest(
                missingPersonId = missingPersonId,
                gender = gender,
                ageGroup = null, // 연령대 처리 로직 필요시 추가
                specialFeature = null, // 특이사항 처리 로직 필요시 추가
                tops = false,
                bottoms = false,
                shoes = false,
                accessories = false,
                hair = false,
                foundImageUrls = null,
                locationFound = locationFound,
                foundLatitude = foundLatitude,
                foundLongitude = foundLongitude,
                additionalDescription = additionalDescription,
                surroundingImageUrls = null,
                additionalReport = null
            )
        } else {
            null
        }
    }

    private fun onReportSuccess() {
        Toast.makeText(context, "제보가 성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show()
        findNavController().navigateUp()
    }

    private fun onReportError(errorMessage: String) {
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}