package com.example.kitharam.api

import android.content.Context
import android.media.session.MediaSession.Token
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {

    private const val BASE_URL = "https://learn-kithara-d6d66497ee18.herokuapp.com/"
    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor{chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()

    val instance: ApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(ApiService::class.java)
    }

//    private const val PREF_NAME = "mySharedPreferences"
//    private const val TOKEN_KEY = "token"
//
//    fun saveToken(context: Context, token: String){
//        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        prefs.edit().putString(TOKEN_KEY, token).apply()
//    }
//
//    private fun getToken(context: Context): String? {
//        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//        return prefs.getString(TOKEN_KEY, null)
//    }
//
//    private class AuthInterceptor(val context: Context): Interceptor{
//        override fun intercept(chain: Interceptor.Chain): Response {
//            val token = getToken(context)
//            val request = chain.request().newBuilder()
//            token?.let{
//                request.addHeader("Authorization", "Bearer $token")
//            }
//            return chain.proceed(request.build())
//        }
//    }
//
//    fun getService(context: Context):ApiService {
//        val loggingInterceptor = HttpLoggingInterceptor().apply {
//            level = HttpLoggingInterceptor.Level.BODY
//        }
//        val authInterceptor = AuthInterceptor(context)
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(loggingInterceptor)
//            .addInterceptor(authInterceptor)
//            .build()
//
//        val retrofit = Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
//            .build()
//
//        return retrofit.create(ApiService::class.java)
//    }
}