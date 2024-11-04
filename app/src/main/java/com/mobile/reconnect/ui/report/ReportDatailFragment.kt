package com.mobile.reconnect.ui.report

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R

class ReportDetailFragment : Fragment(R.layout.fragment_report_datail) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // 전달받은 missingPersonId 값 가져오기
        val missingPersonId = arguments?.getString("missingPersonId")

        // missingPersonId를 사용해 필요한 로직 수행
    }
}