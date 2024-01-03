import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.DatabaseHelper
import com.example.bp2fp.R

class AdapterTransaction(private val transactionList: List<DatabaseHelper.Transaction>) :
    RecyclerView.Adapter<AdapterTransaction.TransactionViewHolder>() {

    class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val doctorNameTextView: TextView = itemView.findViewById(R.id.textViewDoctorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardsession_layout, parent, false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        val transaction = transactionList[position]

        // Bind data to views in the ViewHolder
        holder.doctorNameTextView.text = transaction.namaDoctor
    }


    override fun getItemCount(): Int {
        return transactionList.size
    }
}


