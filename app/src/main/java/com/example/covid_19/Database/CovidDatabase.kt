package com.example.covid_19.Database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.covid_19.pojos.Cases.CasesDataPojo
import com.example.covid_19.pojos.Countries.Country
import com.example.covid_19.pojos.Vaccines.VaccinesDataPojo

@Database (entities = [CasesDataPojo::class, VaccinesDataPojo::class, Country::class], version = 9, exportSchema = false)
abstract class CovidDatabase : RoomDatabase() {
    companion object {
        private var db: CovidDatabase? = null
        private const val DB_NAME = "covid_data.db"
        fun getInstance(context: Context):CovidDatabase {
            if (db != null) {
                db?.let {
                    return it
                }
            }
            val instance = Room.databaseBuilder(context, CovidDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
            db = instance
            return instance
        }

    }
    abstract val covidDao: CovidDao
}