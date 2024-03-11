package com.example.kitharam.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("api/user/register")
    fun register(
        @Field("name")name:String,
        @Field("email")email:String,
        @Field("password")password:String,
        @Field("cpassword")cpassword:String
    ): Call<DefaultResponse>

    @FormUrlEncoded
    @POST("api/user/login")
    fun login(
        @Field("email")email:String,
        @Field("password")password:String
    ): Call<LoginResponse>
}