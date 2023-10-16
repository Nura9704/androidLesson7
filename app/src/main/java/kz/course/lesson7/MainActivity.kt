package kz.course.lesson7

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity(), TimePickerDialog.OnTimeSetListener,
    DatePickerDialog.OnDateSetListener {
    private lateinit var datePickButton: Button
    private lateinit var timePickButton: Button
    private lateinit var registerButton: Button
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private var year: Int = 0
    private var month: Int = 0
    private var dayOfMonth: Int = 0
    private var hourOfDay: Int = 0
    private var minute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        registerPerson()
        pickDate()
        pickTime()
        Toast.makeText(this, resources.getText(R.string.toast_greeting), Toast.LENGTH_LONG).show()
    }

    private fun initViews() {
        datePickButton = findViewById(R.id.datePickButton)
        timePickButton = findViewById(R.id.timePickButton)
        registerButton = findViewById(R.id.registerButton)
        dateEditText = findViewById(R.id.dateEditText)
        timeEditText = findViewById(R.id.timeEditText)
    }

    private fun registerPerson() {
        registerButton.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle(resources.getText(R.string.dialog_title))
            dialog.setMessage(resources.getText(R.string.dialog_message))
            dialog.setNegativeButton(resources.getText(R.string.dialog_no)) {_, _ ->
                showNegativeToast()
            }
            dialog.setPositiveButton(resources.getText(R.string.dialog_yes)) {_, _ ->
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
            dialog.show()
        }
    }

    private fun showNegativeToast() {
        val toast = Toast.makeText(this, resources.getText(R.string.toast_negative), Toast.LENGTH_SHORT)
        toast.show()
    }

    private fun pickDate() {
        val calendar = Calendar.getInstance()
        year = calendar.get(Calendar.YEAR)
        month = calendar.get(Calendar.MONTH)
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
        datePickButton.setOnClickListener {
            val dialog = DatePickerDialog(this, this, year, month, dayOfMonth)
            dialog.show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        dateEditText.setText("$year.$month.$dayOfMonth")
    }

    private fun pickTime() {
        val calendar = Calendar.getInstance()
        hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
        minute = calendar.get(Calendar.MINUTE)
        timePickButton.setOnClickListener {
            val timePicker = TimePickerDialog(this,
                this,
                hourOfDay,
                minute,
                true)
            timePicker.setTitle(resources.getText(R.string.timePickerDialog_title))
            timePicker.show()
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        timeEditText.setText("$hourOfDay : $minute")
    }
}