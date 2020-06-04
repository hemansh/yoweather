package com.example.yoweather.weather

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.yoweather.R
import com.example.yoweather.databinding.WeatherFragmentBinding


class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {

        ViewModelProviders.of(this).get(WeatherViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,

                              savedInstanceState: Bundle?): View? {

        val binding = WeatherFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        withEditText()



        setHasOptionsMenu(true)
        return binding.root
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun withEditText() {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = layoutInflater
        builder.setTitle("Enter Your city name")
        val dialogLayout = inflater.inflate(R.layout.alert_box, null)
        val editText  = dialogLayout.findViewById<EditText>(R.id.editText)
        builder.setView(dialogLayout)
        builder.setPositiveButton("OK") { dialogInterface, i ->
            viewModel.City=editText.text.toString()
            viewModel.getWeatherData()
        }
        builder.show()
    }




}