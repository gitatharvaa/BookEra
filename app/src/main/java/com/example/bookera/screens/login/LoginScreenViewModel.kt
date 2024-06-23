package com.example.bookera.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookera.model.MUser
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class LoginScreenViewModel: ViewModel() {
    // val loadingState = MutableStateFlow(LoadingState.IDLE)
    private val auth: FirebaseAuth = Firebase.auth//here we invoke firebase auth sys. to access it

    private val _loading = MutableLiveData(false)
                          //above is the type that allows us to hold this data and reactive data that
                           //that can be passed along
    val loading: LiveData<Boolean> = _loading //this can be use outside this class


    fun signInWithEmailAndPassword(email: String, password: String, home: () -> Unit )
            = viewModelScope.launch{
        try {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task -> //task holds info that we may want to use in future
                    if (task.isSuccessful){ //whether user is logged in or not
                        Log.d("FB", "signInWithEmailAndPassword: Yay! ${task.result.toString()}")
                        home()//to take us at home screen afterwards
                    }else {
                        Log.d("FB", "signInWithEmailAndPassword: ${task.result.toString()}")
                    }


                }

        }catch (ex: Exception){
            Log.d("FB", "signInWithEmailAndPassword: ${ex.message}")
        }
    }

    fun createUserWithEmailAndPassword(
        email: String,
        password: String,
        home: () -> Unit) {
        if (_loading.value == false) {
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val displayName = task.result?.user?.email?.split('@')?.get(0)
                        //to get the username: vcetatharva@gmail.com -> vcetatharva
                        createUser(displayName)
                        home()
                    } else {
                        Log.d("FB", "createUserWithEmailAndPassword: ${task.result.toString()}")

                    }
                    _loading.value = false
                }
        }
    }

    private fun createUser(displayName: String?) { //add this user in firestore database
        val userId = auth.currentUser?.uid //add userID from auth to firestore database

        val user = MUser(userID = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            quote = "Life is great",
            profession = "Android Developer",
            id = null).toMap()

        FirebaseFirestore.getInstance().collection("users")
            .add(user)
    }
}