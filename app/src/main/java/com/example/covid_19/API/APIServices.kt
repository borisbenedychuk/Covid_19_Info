package com.example.covid_19.API

import com.example.covid_19.pojos.Cases.MainCausesPojo
import com.example.covid_19.pojos.Vaccines.MainVaccinesPojo
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("cases")
    suspend fun getCasesDataByCountry (@Query (PARAM_COUNTRY) country: String): Response<MainCausesPojo>

    @GET("vaccines")
    suspend fun getVaccinesDataByCountry (@Query (PARAM_COUNTRY) country: String): Response<MainVaccinesPojo>

    @GET("vaccines")
    suspend fun getAllCountries (): Response<JsonObject>


    companion object {
        private const val PARAM_COUNTRY = "country"
    }
}