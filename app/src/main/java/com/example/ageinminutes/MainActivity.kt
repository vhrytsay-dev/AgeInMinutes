package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CalendarView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener{
            view -> onClickDatePicker(view)
        }
    }
    
    fun onClickDatePicker(view: View){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, dyear, dmonth, dayOfMonth ->
                val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.GERMAN)
                val selDate = "$dayOfMonth.${dmonth+1}.$dyear"
                val sdfDate = simpleDateFormat.parse(selDate)
                val selectedDateToMinutes = sdfDate!!.time / 60000
                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 60000
                val differentInMinutes = currentDateToMinutes - selectedDateToMinutes
                selectedDate.setText(selDate)
                selectedDateInMinutes.setText(differentInMinutes.toString())

            },year, month, day)
        dpd.datePicker.maxDate = Date().time
        dpd.show()
    }
}