package com.lifescan.handson3.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lifescan.handson3.R
import com.lifescan.handson3.api.ResponseResult

class MyRecyclerViewAdapter(private var itemsList: List<ResponseResult>) :
    RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder>() {

    lateinit var parentImage : ViewGroup

    fun update(itemsList: List<ResponseResult>) {
        this.itemsList = itemsList
        notifyDataSetChanged()
    }


    inner class MyRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //    Pegar campos de: nome e descrição
        val nameTextView: TextView = view.findViewById(R.id.text_view_name)
        val descriptionTextView: TextView = view.findViewById(R.id.text_view_description)
        val likesTextView: TextView = view.findViewById(R.id.text_view_likes)
        val image:ImageView =  view.findViewById(R.id.image_view_profile_picture)


        //    Desagfio: Foto de pergil e numero de curtidas
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecyclerViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        parentImage =  parent

        return MyRecyclerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: MyRecyclerViewHolder, position: Int) {
        val item = itemsList[position]
        holder.nameTextView.text = item.login
        holder.descriptionTextView.text = item.bio
        holder.likesTextView.text = item.followers.toString()

        loadImage(parentImage,holder,item.avatar_url)
    }

    override fun getItemCount(): Int = itemsList.size

    private fun loadImage(parent: ViewGroup, holder: MyRecyclerViewHolder,url: String) {
        Glide.with(parent)
            .load(url)
            .into(holder.image)
    }

}