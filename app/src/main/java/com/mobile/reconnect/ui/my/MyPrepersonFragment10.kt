package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson10Binding
import com.mobile.reconnect.ui.common.BaseFragment

class MyPrepersonFragment10 : BaseFragment<FragmentMyPreperson10Binding>(R.layout.fragment_my_preperson10) {
    private val viewModel: PrePersonViewModel by viewModels()
    private var tempUri: Uri? = null

    private val cameraLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                tempUri?.let { uri ->
                    val fileName = getFileNameFromUri(uri)
                    viewModel.updateSelectedImageName(fileName)
                    binding.button3.text = fileName
                    binding.button3.visibility = View.VISIBLE
                }
            }
        }

    private val galleryLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    val fileName = getFileNameFromUri(uri)
                    viewModel.updateSelectedImageName(fileName)
                    binding.button3.text = fileName
                    binding.button3.visibility = View.VISIBLE
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.camerabutton.setOnClickListener {
            showImagePickerOptions()
        }

        binding.passbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment10_to_prepersonFragment11)
        }

        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment10_to_prepersonFragment11)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.button3.setOnClickListener {
            viewModel.clearSelectedImageName()
            binding.button3.visibility = View.GONE
        }

        // selectedPrePerson이 null인지 확인하여 텍스트 설정
        viewModel.selectedPrePerson.observe(viewLifecycleOwner) { prePerson ->
            binding.button3.text = prePerson?.imageName ?: ""
        }
    }

    private fun showImagePickerOptions() {
        val options = arrayOf("카메라로 촬영", "갤러리에서 선택")
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("사진 첨부하기")
        builder.setItems(options) { _, which ->
            when (which) {
                0 -> openCamera()
                1 -> openGallery()
            }
        }
        builder.show()
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        tempUri = createImageUri()
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri)
        cameraLauncher.launch(cameraIntent)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryIntent.type = "image/*"
        galleryLauncher.launch(galleryIntent)
    }

    private fun createImageUri(): Uri? {
        val contentResolver = requireContext().contentResolver
        val contentValues = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "camera_photo.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        return contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
    }

    private fun getFileNameFromUri(uri: Uri): String {
        var fileName = ""
        requireContext().contentResolver.query(uri, null, null, null, null)?.use { cursor ->
            val nameIndex = cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
            if (nameIndex != -1 && cursor.moveToFirst()) {
                fileName = cursor.getString(nameIndex)
            }
        }
        return fileName
    }
}