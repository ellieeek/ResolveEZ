package com.mobile.reconnect.ui.my

import PrePersonViewModel
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mobile.reconnect.R
import com.mobile.reconnect.databinding.FragmentMyPreperson5Binding
import com.mobile.reconnect.ui.common.BaseFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MyPrepersonFragment5 : BaseFragment<FragmentMyPreperson5Binding>(R.layout.fragment_my_preperson5) {

    private val viewModel: PrePersonViewModel by viewModels()
    private lateinit var currentPhotoPath: String
    private lateinit var photoUri: Uri

    private val imagePickerLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val selectedImageUri: Uri? = result.data?.data
                selectedImageUri?.let { uri ->
                    val imageName = getFileNameFromUri(uri)
                    viewModel.updateSelectedImageName(imageName)
                    binding.button3.text = imageName
                    binding.button3.visibility = View.VISIBLE
                }
            }
        }

    private val cameraLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val file = File(currentPhotoPath)
                if (file.exists()) {
                    val imageName = file.name
                    viewModel.updateSelectedImageName(imageName)
                    binding.button3.text = imageName
                    binding.button3.visibility = View.VISIBLE
                }
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.camerabutton.setOnClickListener {
            showImagePickerOptions()
        }

        binding.addbtn.setOnClickListener {
            findNavController().navigate(R.id.action_prepersonFragment5_to_prepersonFragment6)
        }

        binding.backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.button3.setOnClickListener {
            viewModel.clearSelectedImageName()
            binding.button3.visibility = View.GONE
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

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"
        imagePickerLauncher.launch(intent)
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (cameraIntent.resolveActivity(requireActivity().packageManager) != null) {
            val photoFile: File? = try {
                createImageFile()
            } catch (ex: IOException) {
                null
            }
            photoFile?.let {
                val photoURI = FileProvider.getUriForFile(
                    requireContext(),
                    "${requireContext().packageName}.fileprovider",
                    it
                )
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                cameraLauncher.launch(cameraIntent)
            }
        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!
        return File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun getFileNameFromUri(uri: Uri): String {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
            cursor?.use {
                if (it.moveToFirst()) {
                    result = it.getString(it.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/')
            if (cut != null && cut != -1) {
                result = result?.substring(cut + 1)
            }
        }
        return result ?: "unknown"
    }
}