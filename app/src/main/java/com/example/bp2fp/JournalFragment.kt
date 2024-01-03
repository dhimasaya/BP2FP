package com.example.bp2fp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.model.JournalModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JournalFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JournalFragment: Fragment(), AdapterJournal.OnItemClickListener  {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_journal, container, false)
//        val rvJournal: RecyclerView = view.findViewById(R.id.recyclerJournal)
//        rvJournal.layoutManager = LinearLayoutManager(requireContext())
//
//        val data = ArrayList<JournalModel>()
//        data.add(
//            JournalModel("01", "Melodi Harapan di Sela Hening", "Pikiran:\n" +
//                    "\n" +
//                    "Hari ini aku merasa cukup baik. Aku bangun dengan semangat dan bersemangat untuk memulai hari. Aku melakukan beberapa tugas di rumah, lalu pergi ke taman untuk berjalan-jalan. Udara segar dan pemandangan yang indah membuatku merasa lebih tenang dan damai.\n" +
//                    "\n" +
//                    "Aku juga bertemu dengan teman-temanku untuk makan malam. Kami mengobrol dan tertawa bersama, dan aku merasa sangat bahagia. Aku bersyukur memiliki teman-teman yang peduli dan mendukungku.\n" +
//                    "\n" +
//                    "Emosi:\n" +
//                    "\n" +
//                    "Aku merasa bahagia, bersyukur, dan damai.\n" +
//                    "\n" +
//                    "Tubuh:\n" +
//                    "\n" +
//                    "Tubuhku terasa sehat dan bugar. Aku tidak mengalami gejala fisik apa pun.\n" +
//                    "\n" +
//                    "Tindakan:\n" +
//                    "\n" +
//                    "Aku akan melanjutkan dengan rutinitas harianku dan menikmati waktu bersama teman-teman dan keluarga.\n" +
//                    "\n" +
//                    "Refleksi:\n" +
//                    "\n" +
//                    "Aku bersyukur atas hari yang baik ini. Aku akan berusaha untuk terus menjaga kesehatan mentalku dengan menjalani gaya hidup yang sehat dan positif.\n" +
//                    "\n" +
//                    "Tindakan selanjutnya:\n" +
//                    "\n" +
//                    "Aku akan menjadwalkan waktu untuk berolahraga dan meditasi. Aku juga akan meluangkan waktu untuk membaca buku atau menonton film yang membuatku merasa bahagia.", "23-12-2023")
//        )
//        data.add(JournalModel(R.drawable.icon1,"Journal 2", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "24-12-2023"))
//        data.add(JournalModel(R.drawable.icon1,"Journal 3", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "25-12-2023"))
//        data.add(JournalModel(R.drawable.icon1,"Journal 4", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "26-12-2023"))
//        data.add(JournalModel(R.drawable.icon1,"Journal 5", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum", "27-12-2023"))
//
//        val adapter = AdapterJournal(data, this)
//
//        rvJournal.adapter = adapter


        return view

    }
    override fun onItemClick(journalModel: JournalModel) {
//        val fragment = DetailJournalFragment.newInstance(
//            journalModel.id,
//            journalModel.title,
//            journalModel.content,
//            journalModel.tanggal
//        )
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainer, fragment)
//            .addToBackStack(null)
//            .commit()
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
            JournalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
