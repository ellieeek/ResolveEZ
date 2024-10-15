package com.mobile.reconnect.ui.map

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.mobile.reconnect.data.model.BodyType
import com.mobile.reconnect.data.model.FaceType
import com.mobile.reconnect.data.model.Gender
import com.mobile.reconnect.data.model.MissingPerson
import com.mobile.reconnect.data.model.Nationality
import com.mobile.reconnect.data.model.SpecialFeature
import com.mobile.reconnect.databinding.FragmentHomeBottomBinding
import com.mobile.reconnect.ui.map.adapter.MissingPersonsAdapter
import java.time.LocalDateTime

class HomeBottomFragment : BottomSheetDialogFragment() {

	private lateinit var binding: FragmentHomeBottomBinding // XML 바인딩
	private lateinit var adapter: MissingPersonsAdapter // 어댑터
	private lateinit var persons: List<MissingPerson> // 데이터 리스트

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBottomBinding.inflate(inflater, container, false)
		setupRecyclerView()

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		setupRecyclerView()
	}

	private fun setupRecyclerView() {
		// 예시 데이터
		persons = listOf(
			MissingPerson(
				id = 1,
				name = "홍길동",
				imageURL = "", // 여기에 이미지 URL을 넣을 수 있음
				specialFeature = SpecialFeature.DEMENTIA,
				nationality = Nationality.DOMESTIC,
				gender = Gender.MALE,
				age = 75,
				height = 168,
				weight = 70,
				bodyType = BodyType.AVERAGE,
				faceType = FaceType.OVAL,
				tops = "청색 셔츠",
				bottoms = "검정 바지",
				shoes = "검정 운동화",
				accessories = "안경",
				hair = "짧은 머리",
				lastSeenDateTime = LocalDateTime.now(),
				lastSeenLocation = "서울 강남구"
			),
			MissingPerson(
				id = 2,
				name = "김철수",
				imageURL = "", // 여기에 이미지 URL을 넣을 수 있음
				specialFeature = SpecialFeature.NONE,
				nationality = Nationality.DOMESTIC,
				gender = Gender.MALE,
				age = 80,
				height = 175,
				weight = 80,
				bodyType = BodyType.OBESE,
				faceType = FaceType.SQUARE,
				tops = "흰색 셔츠",
				bottoms = "회색 바지",
				shoes = "검정 구두",
				accessories = "모자",
				hair = "긴 머리",
				lastSeenDateTime = LocalDateTime.now(),
				lastSeenLocation = "부산 해운대구"
			)
		)

		adapter = MissingPersonsAdapter(persons) { person ->
			Log.d("HomeBottomSheetFragment", "Clicked: ${person.name}")
		}

		binding.rvMissingPersonsList.layoutManager = LinearLayoutManager(requireContext())
		binding.rvMissingPersonsList.adapter = adapter
	}
}
