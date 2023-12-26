package com.example.bp2fp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bp2fp.model.NewsModel

private const val ARG_NEWS_IMAGE = "imgNews"
private const val ARG_NEWS_TITLE = "titleNews"
private const val ARG_NEWS_DESC = "descNews"

class DetailNewsFragment : Fragment() {
    private var newsModel: NewsModel? = null

    lateinit var btnBack: ImageView
    lateinit var detailNewsImg: ImageView
    lateinit var detailTxtNews: TextView
    lateinit var detailTitleNews: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            newsModel = NewsModel(
                it.getInt(ARG_NEWS_IMAGE),
                it.getString(ARG_NEWS_TITLE) ?: "",
                it.getString(ARG_NEWS_DESC) ?: ""
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail_news, container, false)

        btnBack = view.findViewById(R.id.btnBackNews)
        detailNewsImg = view.findViewById(R.id.detailNewsImg)
        detailTxtNews = view.findViewById(R.id.detailTxtNews)
        detailTitleNews = view.findViewById(R.id.detailTitleNews)

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        newsModel?.let {
            detailNewsImg.setImageResource(it.image)
            detailTxtNews.text = it.desc
            detailTitleNews.text = it.title
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(imgNews: Int, titleNews: String, descNews: String) =
            DetailNewsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_NEWS_IMAGE, imgNews)
                    putString(ARG_NEWS_TITLE, titleNews)
                    putString(ARG_NEWS_DESC, descNews)
                }
            }
    }
}
