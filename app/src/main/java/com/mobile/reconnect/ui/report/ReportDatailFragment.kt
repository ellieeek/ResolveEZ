package com.mobile.reconnect.ui.report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.databinding.FragmentReportDatailBinding

class ReportDetailFragment : Fragment(R.layout.fragment_report_datail) {

    private var _binding: FragmentReportDatailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportDatailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뒤로 가기 버튼 설정
        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // 전달받은 MissingPerson 객체를 가져와서 UI에 설정
        val missingPerson: MissingPerson? = arguments?.getParcelable("missingPerson")
        missingPerson?.let { person ->
            binding.missingPerson = person
            binding.missingPersonImage.setImageResource(person.imageResId)
            //binding.title.text = "${person.name} (${person.nationality.label}, ${person.gender.label}, ${person.age}세)"
        }

        // reportbtn 버튼 클릭 시 ReportRegistrationFragment로 이동하며 동일한 missingPerson 객체 전달
        binding.reportbtn.setOnClickListener {
            missingPerson?.let { person ->
                val bundle = Bundle().apply {
                    putParcelable("missingPerson", person) // MissingPerson 객체 전달
                }
                findNavController().navigate(R.id.action_reportDetailFragment_to_reportRegistrationFragment, bundle)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}