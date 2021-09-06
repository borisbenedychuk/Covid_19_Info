package com.example.covid_19.pojos.Cases

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


@Entity(tableName = "cases")
data class CasesDataPojo(
    @SerializedName("confirmed")
    @Expose
    val confirmed: Int = 0,

    @SerializedName("recovered")
    @Expose
    val recovered: Int = 0,

    @SerializedName("deaths")
    @Expose
    val deaths: Int = 0,

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
    val lifeExpectancy: Double = 0.0 ,

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
)