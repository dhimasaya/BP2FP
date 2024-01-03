package com.example.bp2fp

import AdapterDoctor
import AdapterTransaction
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class SessionFragment : Fragment(), AdapterDoctor.OnBookingButtonClickListener {
    private lateinit var doctorAdapter: AdapterDoctor
    private lateinit var transactionAdapter: AdapterTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_session, container, false)
        val dbHelper = DatabaseHelper(requireContext())

        dbHelper.insertDoctorData()

        //Doctor
        val doctorList = dbHelper.getDoctors()

        val rvDoctors: RecyclerView = view.findViewById(R.id.recyleViewSession)
        rvDoctors.layoutManager = LinearLayoutManager(requireContext())
        doctorAdapter = AdapterDoctor(requireContext(), doctorList, this)
        rvDoctors.adapter = doctorAdapter

        //Transaction
        val transactionList = dbHelper.getTransaction()

        val rvTransactions: RecyclerView = view.findViewById(R.id.recyleViewAccSession)
        rvTransactions.layoutManager = LinearLayoutManager(requireContext())
        transactionAdapter = AdapterTransaction(transactionList)
        rvTransactions.adapter = transactionAdapter


        return view
    }

    override fun onBookingButtonClick(position: Int) {
        val selectedDoctor = doctorAdapter.getItem(position)

        val intent = Intent(requireContext(), TransactionActivity::class.java)
        intent.putExtra("doctor_id", selectedDoctor.id)
        intent.putExtra("doctor_name", selectedDoctor.name)
        intent.putExtra("doctor_description", selectedDoctor.description)
        intent.putExtra("harga", selectedDoctor.harga)
        startActivity(intent)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SessionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
