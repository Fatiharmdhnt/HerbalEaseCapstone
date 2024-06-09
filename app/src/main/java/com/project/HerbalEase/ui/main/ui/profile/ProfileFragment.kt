package com.project.HerbalEase.ui.main.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.HerbalEase.R
import com.project.HerbalEase.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val profileViewModel =
            ViewModelProvider(this)[ProfileViewModel::class.java]

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nameTextView: TextView = binding.profileName
        val emailTextView: TextView = binding.profileEmail

        profileViewModel.name.observe(viewLifecycleOwner) {
            nameTextView.text = it
        }

        profileViewModel.email.observe(viewLifecycleOwner) {
            emailTextView.text = it
        }

        binding.buttonEdit.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
