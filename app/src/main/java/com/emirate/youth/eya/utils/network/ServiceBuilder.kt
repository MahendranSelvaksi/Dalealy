package com.emirate.youth.eya.utils.network


import com.emirate.youth.eya.utils.AppConstant
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ServiceBuilder {

    private val client = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(getLogging())
        .addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .build()
            chain.let {
                    it.proceed(newRequest)
            }
        }.build()

    private val clientLogin = OkHttpClient.Builder()
        .readTimeout(1, TimeUnit.MINUTES)
        .writeTimeout(1, TimeUnit.MINUTES)
        .connectTimeout(1, TimeUnit.MINUTES)
        .addInterceptor(getLogging())
        .build()


    private fun getLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BASIC
        return logging
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    private val retrofitLogin = Retrofit.Builder()
        .baseUrl(AppConstant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(clientLogin)
        .build()


    fun <T> buildService(service: Class<T>): T {
        return retrofit.create(service)
    }

    fun <T> buildLoginService(service: Class<T>): T {
        return retrofitLogin.create(service)
    }
}