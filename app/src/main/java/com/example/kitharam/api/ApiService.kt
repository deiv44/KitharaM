package com.example.kitharam.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @POST("user/register")
    fun register(@Body user: User): Call<User>

    @POST("login")
    fun login(@Body user: User): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,
        @Field("cpassword")cpassword:String
    )
//    : Call<DefaultResponse>

    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email")email:String,
        @Field("password")password:String
    )
}