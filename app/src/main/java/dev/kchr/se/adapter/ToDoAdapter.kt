package dev.kchr.se.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.kchr.se.R

class ToDoAdapter {

    // Mendefinisikan adapter kustom yang akan digunakan untuk RecyclerView
    class CustomAdapter(private val dataSet: Array<String>) :
        RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

        // Mendefinisikan ViewHolder kustom untuk menangani tampilan setiap item dalam RecyclerView
        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val textView: TextView

            init {
                // Menginisialisasi komponen TextView dari tampilan item
                textView = view.findViewById(R.id.textView)
            }
        }

        // Membuat tampilan baru untuk setiap item dalam RecyclerView
        override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
            // Membuat tampilan baru berdasarkan layout XML menggunakan LayoutInflater
            val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.text_row_item, viewGroup, false)

            // Mengembalikan ViewHolder dengan tampilan baru yang telah dibuat
            return ViewHolder(view)
        }

        // Mengganti isi tampilan pada setiap item dengan data dari dataSet
        override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            // Mengambil elemen data dari dataSet berdasarkan posisi dan mengganti isi TextView dengan elemen tersebut
            viewHolder.textView.text = dataSet[position]
        }

        // Mengembalikan jumlah item dalam dataSet
        override fun getItemCount() = dataSet.size

    }
}


}