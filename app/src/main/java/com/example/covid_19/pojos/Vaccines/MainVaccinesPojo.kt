package com.example.covid_19.pojos.Vaccines

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MainVaccinesPojo(
    @SerializedName("All")
    @Expose
    val vaccinesDataPojo: VaccinesDataPojo? = null
)