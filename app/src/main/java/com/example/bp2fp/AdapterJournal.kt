package com.example.bp2fp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.model.JournalModel

class AdapterJournal(
    private val listJournal: List<JournalModel>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<AdapterJournal.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val id: TextView = itemView.findViewById(R.id.textIdJournal)
        val title: TextView = itemView.findViewById(R.id.textJudulJournal)
        val content: TextView = itemView.findViewById(R.id.textIsiJournal)
        val tanggal: TextView = itemView.findViewById(R.id.textTanggalJournal)
        val btnDetail: Button = itemView.findViewById(R.id.buttonRdNow)
    }

    interface OnItemClickListener {
        fun onItemClick(journalModel: JournalModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.cardnews_layout, parent, false
        )
        return ViewHolder(view)
}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val modelJournal = listJournal[position]

        holder.id.text = modelJournal.id.toString()
        holder.title.text = modelJournal.title
        holder.content.text = modelJournal.content
        holder.tanggal.text = modelJournal.tanggal.toString()

        holder.btnDetail.setOnClickListener {
            onItemClickListener.onItemClick(modelJournal)
        }
    }

    override fun getItemCount(): Int {
        return if (listJournal.size > limit) {
            limit
        } else {
            listJournal.size
        }
    }

    private companion object {
        const val limit = 3
    }
}

