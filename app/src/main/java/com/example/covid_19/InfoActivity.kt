package com.example.covid_19

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.DecimalFormat

class InfoActivity : AppCompatActivity() {

    private lateinit var viewModel: CovidViewModel
    private var country = ""

    private lateinit var countryName: TextView
    private lateinit var confirmed: TextView
    private lateinit var confirmedRate: TextView
    private lateinit var deathes: TextView
    private lateinit var deathRate: TextView

    private lateinit var administered: TextView
    private lateinit var vaccined: TextView
    private lateinit var partiallyVaccined: TextView
    private lateinit var lifeExpectancy: TextView
    private lateinit var countryName2: TextView

    private lateinit var progressBar: ProgressBar
    private lateinit var imageViewLoading: ImageView
    private lateinit var scrollView: ScrollView

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var cardViewCases: CardView
    private lateinit var cardViewVaccines: CardView


    


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)
        countryName = findViewById(R.id.tvCountryName)
        confirmed = findViewById(R.id.tvConfirmedCases)
        confirmedRate = findViewById(R.id.tvConfirmedRate)
        deathes = findViewById(R.id.tvDeathCases)
        deathRate = findViewById(R.id.tvDeathRate)
        countryName2 = findViewById(R.id.tvCountryName2)
        administered = findViewById(R.id.tvAdministered)
        vaccined = findViewById(R.id.tvVaccinated)
        partiallyVaccined = findViewById(R.id.tvPartiallyVaccinated)
        lifeExpectancy = findViewById(R.id.tvLiveExpectancy)
        imageViewLoading = findViewById(R.id.imageViewLoading)
        progressBar = findViewById(R.id.progressBarLoading)
        scrollView = findViewById(R.id.scrollViewInfoActivity)
        constraintLayout = findViewById(R.id.clSecondCard)
        cardViewCases = findViewById(R.id.cardViewCases)
        cardViewVaccines = findViewById(R.id.cardViewVaccines)

        viewModel = ViewModelProvider(this)[CovidViewModel::class.java]

        val intent = intent
        if (intent.hasExtra(COUNTRY)) {
            country = intent?.getStringExtra(COUNTRY) ?: return
        }
        viewModel.casesInfo.observe(this) {
            if (it != null) {
                cardViewCases.visibility = View.VISIBLE
                countryName.text = it.country
                confirmed.text = String.format(resources.getString(R.string.confirmed_cases),
                    DecimalFormat("0,000").format(it.confirmed.toDouble()))
                confirmedRate.text = String.format(resources.getString(R.string.confirmed_rate),
                    DecimalFormat("#0.0").format(it.confirmed.toDouble() / it.population.toDouble() * 100) )
                deathes.text = String.format(resources.getString(R.string.death_cases),
                    DecimalFormat(",###").format(it.deaths))
                deathRate.text = String.format(resources.getString(R.string.death_rate),
                    DecimalFormat("#0.0").format(it.deaths.toDouble() / it.confirmed.toDouble() * 100))
            } else {
                cardViewCases.visibility = View.GONE
            }
        }
        viewModel.vaccinesInfo.observe(this) {
            if (it != null) {
                cardViewVaccines.visibility = View.VISIBLE
                administered.visibility = View.VISIBLE
                vaccined.visibility = View.VISIBLE
                partiallyVaccined.visibility = View.VISIBLE
                lifeExpectancy.visibility = View.VISIBLE
                countryName2.text = it.country
                administered.text = String.format(resources.getString(R.string.administered),
                    DecimalFormat(",###").format(it.administered))
                vaccined.text = String.format(resources.getString(R.string.vaccinated),
                    DecimalFormat(",###").format(it.peopleVaccinated))
                partiallyVaccined.text = String.format(resources.getString(R.string.partially_vaccinated),
                    DecimalFormat(",###").format(it.peoplePartiallyVaccinated))
                lifeExpectancy.text = String.format(resources.getString(R.string.live_expectancy),
                   it.lifeExpectancy)
            } else {
                cardViewVaccines.visibility = View.GONE
            }
        }
        lifecycleScope.launch (Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            imageViewLoading.visibility = View.VISIBLE
            scrollView.visibility = View.GONE
            withContext(Dispatchers.IO) {
                launch { viewModel.loadCases(country) }.join()
                launch { viewModel.loadVaccines(country) }.join()
            }
            progressBar.visibility = View.GONE
            imageViewLoading.visibility = View.GONE
            scrollView.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newIntent(context: Context, country: String): Intent {
            val intent = Intent(context, InfoActivity::class.java)
            intent.putExtra(COUNTRY, country)
            return intent
        }

        private const val COUNTRY = "Country"
    }


}