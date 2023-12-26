// TransactionActivity.kt
package com.example.bp2fp

import AdapterDoctor
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        // Retrieve doctor data from intent
        val doctorName = intent.getStringExtra("doctor_name")
        val doctorDescription = intent.getStringExtra("doctor_description")

        val textViewDoctorName: TextView = findViewById(R.id.namaDokter)
        val textViewDoctorDescription: TextView = findViewById(R.id.descDokter)

        textViewDoctorName.text = "$doctorName"
        textViewDoctorDescription.text = "$doctorDescription"
    }
}
