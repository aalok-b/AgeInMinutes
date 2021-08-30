package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val btnDatePicker = findViewById(R.id.btnDatePicker) as Button
        // set on-click listener
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }

    }


    private fun clickDatePicker(view: View) {
        val MyCalender = Calendar.getInstance()
        val month = MyCalender.get(Calendar.MONTH)
        val year = MyCalender.get(Calendar.YEAR)
        val day = MyCalender.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            Toast.makeText(this, "Date Selected $dayOfMonth", Toast.LENGTH_SHORT).show()

            val selectedDate = "$dayOfMonth/${month + 1}/$year"
            val tvSelectedDate = findViewById(R.id.tvSelectedDate) as TextView

            tvSelectedDate.setText(selectedDate)

            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val theDate = sdf.parse(selectedDate)

            val selectedDateInMinutes = theDate!!.time /60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMinutes = currentDate!!.time / 60000

            val finalAns = currentDateInMinutes - selectedDateInMinutes

            val tvSelectedDateInMinutes = findViewById(R.id.tvSelectedDateInMinutes) as TextView

            tvSelectedDateInMinutes.setText("$finalAns  Min")


            val finalAnsDays = finalAns /1440

            val tvSelectedDateInDays = findViewById(R.id.tvSelectedDateInDays) as TextView

            tvSelectedDateInDays.setText("$finalAnsDays  Days")

        },year, month, day )

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()

    }


}