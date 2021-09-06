package com.example.covid_19

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19.API.APIFactory
import com.example.covid_19.Database.CovidDatabase
import com.example.covid_19.pojos.Countries.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CovidViewModel(application: Application) : AndroidViewModel(application) {

    private val db = CovidDatabase.getInstance(application)
    var casesInfo = db.covidDao.getCasesData()
    var vaccinesInfo = db.covidDao.getVaccinesData()
    var countries = db.covidDao.getCountries()


    suspend fun loadCases(country: String) {
            try {
                val resultCases = APIFactory.apiService().getCasesDataByCountry(country)
                if (resultCases.isSuccessful) {
                    db.covidDao.deleteCases()
                    resultCases.body()?.let {
                        db.covidDao.insertCases(
                            resultCases.body()?.casesDataPojo ?: throw Exception()
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("TEST4", e.message!!)
            }
    }

    suspend fun loadVaccines(country: String) {
            try {
                val resultVaccines = APIFactory.apiService().getVaccinesDataByCountry(country)
                if (resultVaccines.isSuccessful) {
                    db.covidDao.deleteVaccines()
                    resultVaccines.body()?.let {
                        db.covidDao.insertVaccines(
                            resultVaccines.body()?.vaccinesDataPojo ?: throw Exception()
                        )
                    }
                }
            } catch (e: Exception) {
                Log.d("TEST4", e.message!!)
            }
    }

    suspend fun loadCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = mutableListOf<String>()
                val resultCountries = APIFactory.apiService().getAllCountries()
                if (resultCountries.isSuccessful) {
                    val resultS = resultCountries.body()
                    val jsonKeySet = resultS?.keySet()
                    if (jsonKeySet != null) {
                        for (s: String in jsonKeySet) {
                            result.add(s)
                        }
                    }
                    db.covidDao.insertCountries(result.map { Country(it) })
                }
            } catch (e: Exception) {
                Log.d("TEST3", e.message!!)
            }
        }

    }
}