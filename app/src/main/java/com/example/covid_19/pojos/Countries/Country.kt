package com.example.covid_19.pojos.Countries

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(
    @PrimaryKey(autoGenerate = false)
    val name: String
)