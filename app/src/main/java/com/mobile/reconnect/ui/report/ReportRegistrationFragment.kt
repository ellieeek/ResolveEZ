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

class ReportRegistrationFragment : Fragment() {

    private var _binding: FragmentReportRegistrationBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val CAMERA_REQUEST_CODE = 1001
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportRegistrationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 전달받은 MissingPerson 객체를 가져와서 UI에 설정
        val missingPerson: MissingPerson? = arguments?.getParcelable("missingPerson")
        missingPerson?.let { person ->
            binding.missingPerson = person // 데이터 바인딩에 설정
        }

        // 카메라 호출 기능 설정
        binding.frameLayout3.setOnClickListener {
            openCamera()
        }
        binding.frameLayout5.setOnClickListener {
            openCamera()
        }

        // backButton 클릭 시 이전 화면으로 이동
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // reportbtn 클릭 시 MyReportFragment로 이동
        binding.reportbtn.setOnClickListener {
            findNavController().navigate(R.id.action_reportRegistrationFragment_to_myReportFragment)
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}