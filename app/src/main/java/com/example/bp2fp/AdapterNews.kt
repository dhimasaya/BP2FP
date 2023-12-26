package com.example.bp2fp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.model.NewsModel

class AdapterNews(
    private val listNews: List<NewsModel>, private val onItemClickListener: NewsFragment
) :
    RecyclerView.Adapter<AdapterNews.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.coverNews)
        val title: TextView = itemView.findViewById(R.id.txtNewsTitle)
        val desc: TextView = itemView.findViewById(R.id.txtNewsDesc)
        val btnDetail: Button = itemView.findViewById(R.id.buttonRdNow)
    }

    interface OnItemClickListener {
        fun onItemClick(newsModel: NewsModel)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cardnews_layout, parent, false
        )
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelNews = listNews[position]

        holder.image.setImageResource(modelNews.image)
        holder.title.text = modelNews.title
        holder.desc.text = modelNews.desc

        holder.btnDetail.setOnClickListener {
            onItemClickListener.onItemClick(modelNews)
        }
    }

    private val limit = 3

    override fun getItemCount(): Int {
        if (listNews.size > limit) {
            return limit;
        } else {
            return listNews.size;
        }
    }
}
