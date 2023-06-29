package ddwu.com.mobile.adaptereventtest

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.adaptereventtest.databinding.ListItemBinding

class FoodAdapter (val foods : ArrayList<FoodDto>)
    : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    interface OnItemLongClickListener {
        fun OnItemLongClick(view: View, position: Int)
    }

    lateinit var listener : OnItemLongClickListener

    fun setOnItemListener(listener: OnItemLongClickListener) {
        this.listener = listener
    }



    override fun getItemCount() = foods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FoodViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.photo.setImageResource(foods[position].photo)
        holder.food.text = foods[position].food
        holder.count.text = foods[position].count.toString()
    }

//    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val photo = view.findViewById<ImageView>(R.id.ivPhoto)
//        val food = view.findViewById<TextView>(R.id.tvFood)
//        val count = view.findViewById<TextView>(R.id.tvCount)
//    }

    class FoodViewHolder(view: View, listener: OnItemLongClickListener)
        : RecyclerView.ViewHolder(view) {

        val photo = view.findViewById<ImageView>(R.id.ivPhoto)
        val food = view.findViewById<TextView>(R.id.tvFood)
        val count = view.findViewById<TextView>(R.id.tvCount)

        init {
//            val TAG = "ViewHolder"
//            itemView.setOnClickListener {
////                Log.d(TAG, "${adapterPosition} click!!!")
//                listener.OnItemLongClick(itemView, adapterPosition)
//            }

            itemView.setOnLongClickListener {
                listener.OnItemLongClick(itemView, adapterPosition)

                true
            }
        }
        }
    }

