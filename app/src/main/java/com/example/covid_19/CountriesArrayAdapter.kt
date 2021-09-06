package com.example.covid_19

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CountriesArrayAdapter(context: Context) : ArrayAdapter<String> (context, R.layout.array_item, listOf()) {

    var listOfCountries = listOf<String>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val country = listOfCountries[position]
        val view =
          LayoutInflater.from(parent.context).inflate(R.layout.array_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.tvList_item)
        textView.text = country
        return view
    }

    override fun getCount(): Int {
        return listOfCountries.size
    }

    override fun getItem(position: Int): String {
        return listOfCountries[position]
    }
}