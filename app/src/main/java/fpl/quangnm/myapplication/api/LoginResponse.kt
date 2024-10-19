package fpl.quangnm.myapplication.api

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val token: String?
)