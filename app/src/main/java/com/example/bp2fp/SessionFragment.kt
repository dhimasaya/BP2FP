package com.example.bp2fp

import AdapterDoctor
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.DatabaseHelper
import com.example.bp2fp.R
import com.example.bp2fp.TransactionActivity

class SessionFragment : Fragment(), AdapterDoctor.OnBookingButtonClickListener {

    private lateinit var doctorAdapter: AdapterDoctor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_session, container, false)
        val dbHelper = DatabaseHelper(requireContext())
        dbHelper.insertDoctorData()

        // DATA FROM DATABASE
        val doctorList = dbHelper.getDoctors()
        doctorAdapter = AdapterDoctor(requireContext(), doctorList, this)

        // AMBIL DATA DARI ADAPTER
        val rvDoctors: RecyclerView = view.findViewById(R.id.recyleViewSession)
        rvDoctors.layoutManager = LinearLayoutManager(requireContext())
        rvDoctors.adapter = doctorAdapter

        return view
    }

    override fun onBookingButtonClick(position: Int) {
        val selectedDoctor = doctorAdapter.getItem(position)

        val intent = Intent(requireContext(), TransactionActivity::class.java)
        intent.putExtra("doctor_id", selectedDoctor.id)
        intent.putExtra("doctor_name", selectedDoctor.name)
        intent.putExtra("doctor_description", selectedDoctor.description)
        intent.putExtra("harga",selectedDoctor.harga)
        startActivity(intent)
    }
}
