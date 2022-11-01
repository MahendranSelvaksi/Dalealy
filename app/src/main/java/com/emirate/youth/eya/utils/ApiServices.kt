package com.nextgen.machinetask.network

import com.google.gson.JsonObject
import com.emirate.youth.eya.utils.model.LoginModel
import com.emirate.youth.eya.utils.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface ApiServices {
    @Headers("Content-Type: application/json")
    @POST("login")
    fun login(@Body jsonObject: JsonObject): Call<LoginModel>

    @GET("users")
    fun getUser():Call<UserModel>
}