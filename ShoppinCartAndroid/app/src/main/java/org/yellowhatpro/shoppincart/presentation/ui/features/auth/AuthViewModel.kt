package org.yellowhatpro.shoppincart.presentation.ui.features.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.yellowhatpro.shoppincart.data.repositories.FirebaseRepository
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository) : ViewModel() {

    fun addUserToFirebase(id : String){
        viewModelScope.launch {
            firebaseRepository.setUserByID(id)
        }
    }
}