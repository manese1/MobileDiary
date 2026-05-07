package com.manese.mobilediary.data

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.manese.mobilediary.states.AuthState

class AuthViewModel : ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val database = FirebaseDatabase.getInstance().reference

    // 🔥 UI State
    private val _loginState = MutableStateFlow<AuthState>(AuthState.Idle)
    val loginState: StateFlow<AuthState> = _loginState

    fun login(email: String, password: String) {

        if (email.isBlank() || password.isBlank()) {
            _loginState.value = AuthState.Error("Fill all fields")
            return
        }

        _loginState.value = AuthState.Loading

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid

                    if (uid != null) {

                        database.child("users").child(uid).get()
                            .addOnSuccessListener { snapshot ->

                                val name = snapshot.child("name").value?.toString() ?: "User"
                                val role = snapshot.child("role").value?.toString() ?: "STUDENT"

                                _loginState.value = AuthState.Success(name, role)
                            }
                            .addOnFailureListener {
                                _loginState.value = AuthState.Error("Failed to fetch user data")
                            }
                    }

                } else {
                    _loginState.value = AuthState.Error(
                        task.exception?.message ?: "Login failed"
                    )
                }
            }
    }

    fun register(name: String, email: String, password: String, role: String) {

        if (name.isBlank() || email.isBlank() || password.isBlank()) {
            _loginState.value = AuthState.Error("Fill all fields")
            return
        }

        _loginState.value = AuthState.Loading

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->

                if (task.isSuccessful) {

                    val uid = auth.currentUser?.uid

                    if (uid != null) {

                        val userMap = mapOf(
                            "name" to name,
                            "email" to email,
                            "role" to role
                        )

                        database.child("users").child(uid).setValue(userMap)
                            .addOnSuccessListener {
                                _loginState.value = AuthState.Success(name, role)
                            }
                            .addOnFailureListener {
                                _loginState.value = AuthState.Error("Failed to save user")
                            }
                    }

                } else {
                    _loginState.value = AuthState.Error(
                        task.exception?.message ?: "Registration failed"
                    )
                }
            }
    }
}

