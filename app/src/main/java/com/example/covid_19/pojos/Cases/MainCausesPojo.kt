package com.example.covid_19.pojos.Cases

import com.example.covid_19.pojos.Cases.CasesDataPojo
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




data class MainCausesPojo (
    @SerializedName("All")
    @Expose
    val casesDataPojo: CasesDataPojo? = null
)