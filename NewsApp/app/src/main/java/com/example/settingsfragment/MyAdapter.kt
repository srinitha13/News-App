package com.example.settingsfragment



import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("UNREACHABLE_CODE")
class MyAdapter(private val listener: OnclickItem) : RecyclerView.Adapter<MyViewHolder>() {

    private val items : ArrayList<newsContent> = ArrayList()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.news_item,parent,false)
        val viewHolder = MyViewHolder(view)
        view.setOnClickListener{
            listener.onClicked(items[viewHolder.adapterPosition])
        }

        return viewHolder
    }





    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val cItem = items[position]
        holder.titleView.text =  cItem.title
        Glide.with(holder.itemView.context).load(cItem.imageUrl).into(holder.Image)
    }
    fun updatedNews(updatedNews : ArrayList<newsContent>){
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {

        return items.size

        Log.d("adapter","items are${items.size}")
    }
}



class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleView :TextView = itemView.findViewById(R.id.newsTitle)
    val Image : ImageView = itemView.findViewById(R.id.newsImage)
}




public interface OnclickItem {
    fun onClicked(item :newsContent)
}

