package com.example.bp2fp

import AdapterDoctor
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)

        val doctorName = intent.getStringExtra("doctor_name")
        val doctorDescription = intent.getStringExtra("doctor_description")

        val buttonPay: Button = findViewById(R.id.btnNext)
        val textViewDoctorName: TextView = findViewById(R.id.namaDokter)
        val textViewDoctorDescription: TextView = findViewById(R.id.descDokter)


        textViewDoctorName.text = "$doctorName"
        textViewDoctorDescription.text = "$doctorDescription"

        buttonPay.setOnClickListener {
            val intent = Intent(
                this@TransactionActivity,
                PaymentActivity::class.java
            )
            startActivity(intent)

        }
    }
}
