package com.example.model1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.util.Log
import androidx.activity.ComponentActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : ComponentActivity() {

    private lateinit var privateIndikator: TextView
    private lateinit var privateTextView: TextView
    private lateinit var database: DatabaseReference
    private lateinit var lineChart: LineChart
    private var secondsCounter = 0f // Initialize a counter for seconds
    private val entries = mutableListOf<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //textview nilai
        privateTextView = findViewById(R.id.nilaipoten)
        privateIndikator = findViewById(R.id.indikator)
        lineChart = findViewById(R.id.lineChart)
        database = FirebaseDatabase.getInstance().reference

        initializeChart()
        fetchDataFromFirebase()

        //potenpotenan
        val statusListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val stat = dataSnapshot.getValue(String::class.java)
                stat?.let {
                    privateIndikator.text = it
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("main activity", "load data cancelled", databaseError.toException())
            }
        }

        //punya potentsio
        val dataListener = object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.getValue(Int::class.java)
                data?.let {
                    privateTextView.text = "$it"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                Log.w("main activity", "load data cancelled", databaseError.toException())
            }

        }

        database.child("data").addValueEventListener(dataListener)
        database.child("Status").addValueEventListener(statusListener)
        //firebase nya
        FirebaseApp.initializeApp(this)


    }

    private fun initializeChart() {
        val dataSet = LineDataSet(entries, "Nilai Potensiometer")
        dataSet.color = resources.getColor(R.color.blue, null)
        dataSet.valueTextColor = resources.getColor(R.color.black, null)
        dataSet.valueTextSize = 12f
        dataSet.setDrawCircles(true)
        dataSet.setCircleColor(resources.getColor(R.color.red, null))

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.setBackgroundColor(resources.getColor(R.color.white, null))
        lineChart.setDrawGridBackground(false)

        lineChart.animateX(1000)
        lineChart.animateY(1000)


        val yAxis = lineChart.axisRight
        yAxis.setDrawLabels(false)
        yAxis.setDrawGridLines(false)

        val xAxis = lineChart.xAxis
        xAxis.setDrawGridLines(false)

        xAxis.setDrawAxisLine(true)
        xAxis.axisLineColor = resources.getColor(R.color.black, null)
        xAxis.granularity = 1f

        val description = Description()
        description.text = ""
        lineChart.description = description
    }

    private fun fetchDataFromFirebase() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()

        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val data = dataSnapshot.child("data").getValue(Float::class.java) ?: return
                // Update chart data
                entries.add(Entry(secondsCounter, data))
                setDataToChart(entries)

                // Increment seconds counter
                secondsCounter += 1f
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })
    }

    private fun setDataToChart(entries: List<Entry>) {
        val dataSet = LineDataSet(entries, "Nilai Potensiometer")
        dataSet.color = resources.getColor(R.color.blue, null)
        dataSet.valueTextColor = resources.getColor(R.color.black, null)
        dataSet.valueTextSize = 12f
        dataSet.setDrawCircles(true)
        dataSet.setCircleColor(resources.getColor(R.color.red, null))

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        lineChart.invalidate() // Refresh the chart with new data
    }

}


