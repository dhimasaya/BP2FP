import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bp2fp.DatabaseHelper
import com.example.bp2fp.R

class AdapterDoctor(
    private val context: Context,
    private val doctorList: List<DatabaseHelper.Doctor>,
    private val onBookingButtonClickListener: OnBookingButtonClickListener
) : RecyclerView.Adapter<AdapterDoctor.ViewHolder>() {

    interface OnBookingButtonClickListener {
        fun onBookingButtonClick(position: Int)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.namaDokter)
        val descTextView: TextView = itemView.findViewById(R.id.descDokter)
        val btnBooking: Button = itemView.findViewById(R.id.btnBooking)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.carddoctor_layout, parent, false
        )
        return ViewHolder(view)
    }

    fun getItem(position: Int): DatabaseHelper.Doctor {
        return doctorList[position]
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentDoctor = doctorList[position]

        holder.nameTextView.text = currentDoctor.name
        holder.descTextView.text = currentDoctor.description

        // Handle button click for the item at the specified position
        holder.btnBooking.setOnClickListener {
            onBookingButtonClickListener.onBookingButtonClick(position)
        }
    }

    override fun getItemCount(): Int {
        return doctorList.size
    }
}
