package com.project.HerbalEase.ui.main.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _name = MutableLiveData<String>().apply {
        value = "John Doe"
    }
    val name: LiveData<String> = _name

    private val _email = MutableLiveData<String>().apply {
        value = "john.doe@example.com"
    }
    val email: LiveData<String> = _email

    fun updateName(newName: String) {
        _name.value = newName
    }

    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }
}
