package com.example.differenceintime

import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var startTime: Calendar
    private lateinit var endTime: Calendar
    private lateinit var tvTimeDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startTime = Calendar.getInstance()
        endTime = Calendar.getInstance()
        tvTimeDifference = findViewById(R.id.tvTimeDifference)
    }

    fun showStartTimePicker(view: android.view.View) {
        showTimePicker(startTime)
    }

    fun showEndTimePicker(view: android.view.View) {
        showTimePicker(endTime)
    }

    private fun showTimePicker(calendar: Calendar) {
        val timePickerDialog = TimePickerDialog(
            this,
            { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }

    fun calculateTimeDifference(view: android.view.View) {
        if (!this::startTime.isInitialized || !this::endTime.isInitialized) {
            return
        }

        val timeDifferenceInMillis = endTime.timeInMillis - startTime.timeInMillis
        val hours = timeDifferenceInMillis / (1000 * 60 * 60)
        val minutes = (timeDifferenceInMillis % (1000 * 60 * 60)) / (1000 * 60)

        val timeDifferenceText = String.format(
            Locale.getDefault(),
            "Time difference: %02d:%02d",
            hours,
            minutes
        )
        tvTimeDifference.text =  timeDifferenceText
    }
}
