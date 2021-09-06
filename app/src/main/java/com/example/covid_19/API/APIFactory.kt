package com.example.covid_19.API

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object APIFactory {

    private const val BASE_URL = "https://covid-api.mmediagroup.fr/v1/"

    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    fun apiService () = retrofit.create(APIServices::class.java)

}