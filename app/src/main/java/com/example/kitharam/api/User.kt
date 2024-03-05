package com.example.kitharam.api

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id: String? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("email")
    val email: String,

    @SerializedName("password")
    val password: String
)

data class LoginResponse(
    @SerializedName("token")
    val token: String,

    @SerializedName("user")
    val user: User
)