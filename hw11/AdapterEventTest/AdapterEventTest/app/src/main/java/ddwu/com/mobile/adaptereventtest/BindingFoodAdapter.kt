package ddwu.com.mobile.adaptereventtest

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ddwu.com.mobile.adaptereventtest.databinding.ListItemBinding

class BindingFoodAdapter (val foods : ArrayList<FoodDto>)
    : RecyclerView.Adapter<BindingFoodAdapter.FoodViewHolder>(){

    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    lateinit var listener : OnItemClickListener

    fun setOnItemListener(listener: OnItemClickListener) {
        this.listener = listener
    }





    override fun getItemCount() = foods.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FoodViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.itemBinding.ivPhoto.setImageResource(foods[position].photo)
        holder.itemBinding.tvFood.text = foods[position].food
        holder.itemBinding.tvCount.text = foods[position].count.toString()
    }

    class FoodViewHolder(val itemBinding: ListItemBinding, listener: OnItemClickListener)
        : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            val TAG = "ViewHolder"
            itemBinding.root.setOnClickListener {
//                Log.d(TAG, "${adapterPosition} click!!!")
                listener.onItemClick(itemBinding.root, adapterPosition)
            }

            itemBinding.root.setOnLongClickListener {

                true
            }
        }
    }

}