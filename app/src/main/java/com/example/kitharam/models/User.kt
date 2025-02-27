package com.example.kitharam.models

data class User(
    val username: String = "",
    val email: String = "",
    val level: String = "" ,
    val txtTitle: String = "",
    val txtDescription: String = ""// New field to determine the user's level
)


