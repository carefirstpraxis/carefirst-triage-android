package com.carefirstpraxis.carefirst_triage_android

import DataModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RetrofitAPI {
  @POST("login")
  @FormUrlEncoded
  fun login(
    @Field("data") data: String
  ): Call<DataModel?>?
}