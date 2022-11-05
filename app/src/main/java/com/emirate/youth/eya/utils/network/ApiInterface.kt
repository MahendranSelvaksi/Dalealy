package com.emirate.youth.eya.utils.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*
import retrofit2.http.Body

interface ApiInterface {

    @GET("Login")
    fun login(@Query("emirateId")emirateId:String,@Query("password")password:String): Call<ResponseBody>

    @GET("register")
    fun register(@Query("name")emirateId:String,@Query("student_no")student_no:String,@Query("schName")schName:String,
                 @Query("edupath")edupath:String,@Query("class")class1:String,
                 @Query("email_id")email_id:String,@Query("contact_no")contact_no:String,
                 @Query("identifier_no")identifier_no:String,@Query("dob")dob:String,
                 @Query("interest")interest:String,@Query("emirates_id")emirates_id:String,
                 @Query("resident_no")resident_no:String,@Query("password")password:String,
                 @Query("arabic")arabic:String,@Query("english")english:String,
                 @Query("maths")maths:String,@Query("chemistry")chemistry:String,
                 @Query("physics")physics:String,@Query("biology")biology:String): Call<ResponseBody>

    @GET("FetchQuestionnaire")
    fun FetchQuestionnaire(): Call<ResponseBody>

    @GET("FetchSchool")
    fun FetchSchool(): Call<ResponseBody>

    @GET("NewUserList")
    fun NewUserList(): Call<ResponseBody>
}