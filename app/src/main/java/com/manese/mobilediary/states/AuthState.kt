package com.manese.mobilediary.states

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val name: String, val role: String) : AuthState()
    data class Error(val message: String) : AuthState()
}