package com.example.covid_19

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {


    private lateinit var searchView: SearchView
    private lateinit var listView: ListView

    private lateinit var explore: TextView
    private lateinit var covid: TextView
    private lateinit var data: TextView


    private lateinit var countriesAdapter: CountriesArrayAdapter

    private val viewModel: CovidViewModel by lazy {
        ViewModelProvider(this)[CovidViewModel::class.java]
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listView)
        countriesAdapter = CountriesArrayAdapter(this@MainActivity)
        listView.adapter = countriesAdapter
        searchView = findViewById(R.id.searchViewCountry)
        viewModel.countries.observe(this@MainActivity) {
            lifecycleScope.launch(Dispatchers.Main) {
                if (it.isEmpty()) {
                    withContext(Dispatchers.IO) {
                        viewModel.loadCountries()
                    }
                }
                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let { query ->
                            for (i in it) {
                                if (query == i.name) {
                                    val intent =
                                        InfoActivity.newIntent(this@MainActivity, query)
                                    startActivity(intent)
                                }
                            }
                        }
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {

                        if (newText!!.isNotEmpty()) {
                            countriesAdapter.listOfCountries =
                                it.map { it.name }.filter { it.startsWith(newText, true) }
                            listView.visibility = View.VISIBLE
                            explore.visibility = View.GONE
                            covid.visibility = View.GONE
                            data.visibility = View.GONE

                        } else {
                            explore.visibility = View.VISIBLE
                            covid.visibility = View.VISIBLE
                            data.visibility = View.VISIBLE
                            listView.visibility = View.GONE
                        }
                        return false
                    }
                })
            }
        }

        explore = findViewById(R.id.textViewExplore)
        covid = findViewById(R.id.textViewCovid)
        data = findViewById(R.id.textViewData)

        listView.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val value = listView.adapter.getItem(position).toString()
                val intent = InfoActivity.newIntent(this@MainActivity, value)
                startActivity(intent)
            }
    }

}