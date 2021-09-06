package com.example.covid_19.pojos.Vaccines

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


@Entity(tableName = "vaccines")
data class VaccinesDataPojo(
    @SerializedName("administered")
    @Expose
    val administered: Int = 0,

    @SerializedName("people_vaccinated")
    @Expose
    val peopleVaccinated: Int = 0,

    @SerializedName("people_partially_vaccinated")
    @Expose
    val peoplePartiallyVaccinated: Int = 0,

    @SerializedName("country")
    @Expose
    val country: String? = null,

    @SerializedName("population")
    @Expose
    val population: Int = 0,

    @SerializedName("sq_km_area")
    @Expose
    val sqKmArea: Int = 0,

    @SerializedName("life_expectancy")
    @Expose
    val lifeExpectancy: Double = 0.0,

    @SerializedName("elevation_in_meters")
    @Expose
    val elevationInMeters: Int = 0,

    @SerializedName("continent")
    @Expose
    val continent: String? = null,

    @SerializedName("abbreviation")
    @Expose
    val abbreviation: String? = null,

    @SerializedName("location")
    @Expose
    val location: String? = null,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("iso")
    @Expose
    val iso: Int = 0,

    @SerializedName("capital_city")
    @Expose
    val capitalCity: String? = null,

    @SerializedName("updated")
    @Expose
    val updated: String? = null
)