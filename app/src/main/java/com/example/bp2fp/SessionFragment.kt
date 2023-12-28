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
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var doctorAdapter: AdapterDoctor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment NewsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            NewsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
