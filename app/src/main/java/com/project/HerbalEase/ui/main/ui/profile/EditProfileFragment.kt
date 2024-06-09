package com.project.HerbalEase.ui.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.HerbalEase.databinding.FragmentEditProfileBinding

class EditProfileFragment : Fragment() {

    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private val GALLERY_REQUEST_CODE = 100


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nameEditText: EditText = binding.profileName
        val emailEditText: EditText = binding.profileEmail
        val saveButton: Button = binding.buttonSave

        profileViewModel.name.observe(viewLifecycleOwner) {
            nameEditText.setText(it)
        }

        profileViewModel.email.observe(viewLifecycleOwner) {
            emailEditText.setText(it)
        }

        saveButton.setOnClickListener {
            profileViewModel.updateName(nameEditText.text.toString())
            profileViewModel.updateEmail(emailEditText.text.toString())
            findNavController().navigateUp()
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().popBackStack()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
