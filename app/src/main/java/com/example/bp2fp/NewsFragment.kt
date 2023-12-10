package com.example.bp2fp

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
class NewsFragment : Fragment(), AdapterNews.OnItemClickListener {
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
        val view = inflater.inflate(R.layout.fragment_news, container, false)
        val rvNews:RecyclerView = view.findViewById(R.id.recyleViewNews)
        rvNews.layoutManager = LinearLayoutManager(requireContext())

        val data = ArrayList<NewsModel>()
        data.add(NewsModel(R.drawable.profile_pict,"Pentingnya Kesehatan Mental Bagi Remaja", "Kesehatan mental merupakan aspek penting yang seringkali terabaikan dalam kehidupan sehari-hari. Terutama bagi remaja, menjaga kesehatan mental remaja memiliki peranan yang sangat penting untuk memastikan perkembangan yang sehat dan kualitas hidup yang baik. Kali ini membahas secara singkat pentingnya kesehatan mental bagi remaja dan memberikan beberapa tips tentang bagaimana remaja dapat menjaga kesehatan mental remaja dengan baik.\n" +
                "\n" +
                "Remaja adalah kelompok usia yang rentan mengalami berbagai perubahan emosional dan psikologis yang signifikan. Oleh karena itu, menjaga kesehatan mental remaja menjadi suatu kebutuhan yang mendesak. Kesehatan mental yang baik dapat berkontribusi pada kemampuan remaja dalam menghadapi tekanan, mengatasi masalah, dan menjalin hubungan sosial yang sehat.\n" +
                "\n" +
                "Ada beberapa cara yang dapat dilakukan oleh remaja untuk menjaga kesehatan mental remaja. Pertama-tama, penting bagi remaja untuk menjaga keseimbangan antara tugas akademik, aktivitas sosial, dan waktu istirahat. Terlalu banyak tekanan akademik dapat menyebabkan stres yang berlebihan dan mempengaruhi kesehatan mental. Dengan mengatur waktu dengan baik dan mengambil istirahat yang cukup, remaja dapat mengurangi risiko stres dan menjaga keseimbangan dalam kehidupan remaja.\n" +
                "\n" +
                "Selain itu, penting bagi remaja untuk memiliki outlet kreatif atau hobi yang dapat membantu remaja mengekspresikan diri. Misalnya, remaja dapat mencoba seni, olahraga, menulis, atau bermain musik. Kegiatan ini dapat membantu mengurangi stres, meningkatkan mood, dan memberikan rasa pencapaian yang positif.\n" +
                "\n" +
                "Remaja juga perlu menjaga pola makan yang sehat dan berimbang. Makan makanan bergizi dapat memberikan energi yang cukup untuk menjaga kesehatan fisik dan mental. Hindari mengonsumsi makanan cepat saji atau junk food yang dapat mempengaruhi suasana hati dan kesejahteraan secara keseluruhan.\n" +
                "\n" +
                "Lebih lanjut, penting bagi remaja untuk membangun hubungan sosial yang positif dan mendapatkan dukungan dari orang-orang terdekat. Bekerjasama dengan keluarga, teman, atau bahkan mencari bantuan dari profesional kesehatan mental dapat membantu remaja mengatasi masalah yang remaja hadapi dan merasa didukung secara emosional.\n" +
                "\n" +
                "Jika kesehatan mental tidak dijaga dengan baik, remaja berisiko mengalami gangguan mental yang dapat mempengaruhi kehidupan remaja secara negatif. Beberapa ciri-ciri yang mungkin muncul ketika mengalami gangguan mental termasuk perubahan suasana hati yang drastis, kesulitan tidur, kehilangan minat atau motivasi, perubahan berat badan yang signifikan, isolasi sosial, dan kesulitan dalam konsentrasi. Jika remaja mengalami gejala-gejala ini, penting untuk segera mencari bantuan dari profesional kesehatan mental.\n" +
                "\n" +
                "Dalam kesimpulan, kesehatan mental merupakan aspek yang sangat penting bagi remaja. Dengan menjaga kesehatan mental remaja dengan baik, remaja dapat menghadapi tantangan hidup dengan lebih baik, menjalin hubungan yang sehat, dan meraih kualitas hidup yang baik. Penting bagi remaja untuk mengenali tanda-tanda gangguan mental dan mencari bantuan jika diperlukan. Dengan dukungan yang tepat, remaja dapat tumbuh dan berkembang secara optimal dalam aspek kesehatan mental remaja."))
        data.add(NewsModel(R.drawable.icon1,"Berita 2", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        data.add(NewsModel(R.drawable.icon1,"Berita 3", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        data.add(NewsModel(R.drawable.icon1,"Berita 4", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))
        data.add(NewsModel(R.drawable.icon1,"Berita 5", "Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum Lorem Ipsum"))

        val adapter = AdapterNews(data, this)

        rvNews.adapter = adapter


        return view

    }
    override fun onItemClick(newsModel: NewsModel) {
        val fragment = DetailNewsFragment.newInstance(
            newsModel.image,
            newsModel.title,
            newsModel.desc
        )
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
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