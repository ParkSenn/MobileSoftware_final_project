package ddwucom.mobile.finalreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ddwucom.mobile.finalreport.data.MovieDto
import ddwucom.mobile.finalreport.databinding.ActivityMainBinding
import ddwucom.mobile.finalreport.databinding.ItemListBinding

class MovieAdapter (val movies : ArrayList<MovieDto>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    override fun getItemCount(): Int = movies.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemBinding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieViewHolder(itemBinding, cListener, clListener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.itemBinding.ivPoster.setImageResource(movies[position].poster)
        holder.itemBinding.tvTitle.text = movies[position].title
        holder.itemBinding.tvDirector.text = movies[position].director
        holder.itemBinding.tvActor.text = movies[position].actor
        holder.itemBinding.tvRate.text = movies[position].rate.toString()
    }

    class MovieViewHolder(val itemBinding: ItemListBinding, cListener: OnItemClickListener?, clListener: OnItemLongClickListener?)
        : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemBinding.root.setOnClickListener(object: View.OnClickListener {
                override fun onClick(view: View?) {
                    cListener?.onItemClick(view!!, adapterPosition)
                }
            })

            itemBinding.root.setOnLongClickListener(object: View.OnLongClickListener {
                override fun onLongClick(view: View?):Boolean {
                    clListener?.onItemLongClick(view!!, adapterPosition)
                    return true
                }
            })
        }
    }

    // item 클릭하면 수정
    interface OnItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    var cListener : OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.cListener = listener
    }

    // item 롱클릭하면 삭제
    interface OnItemLongClickListener {
        fun onItemLongClick(view: View, position: Int)
    }

    var clListener : OnItemLongClickListener? = null

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        this.clListener = listener
    }



}