package com.example.covid_19.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.covid_19.pojos.Cases.CasesDataPojo
import com.example.covid_19.pojos.Countries.Country
import com.example.covid_19.pojos.Vaccines.VaccinesDataPojo

@Dao
interface CovidDao {

    @Query("SELECT * FROM vaccines")
    fun getVaccinesData(): LiveData<VaccinesDataPojo>

    @Query("SELECT * FROM cases")
    fun getCasesData(): LiveData<CasesDataPojo>

    @Query ("SELECT * FROM countries")
    fun getCountries(): LiveData<List<Country>>

    @Insert
    suspend fun insertVaccines (vaccinesDataPojo: VaccinesDataPojo)

    @Insert
    suspend fun insertCases (casesDataPojo: CasesDataPojo)

    @Query("DELETE FROM cases")
    suspend fun deleteCases ()

    @Query("DELETE FROM vaccines")
    suspend fun deleteVaccines ()

    @Insert
    suspend fun insertCountries (list: List<Country>)

}