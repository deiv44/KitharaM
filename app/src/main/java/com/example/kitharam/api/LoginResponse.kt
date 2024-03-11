package com.example.kitharam.api

data class LoginResponse (
    val error: Boolean,
    val message:String,
    val user: User
)