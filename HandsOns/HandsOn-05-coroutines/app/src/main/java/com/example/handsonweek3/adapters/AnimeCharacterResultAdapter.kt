package com.example.handsonweek3.adapters

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.handsonweek3.R
import com.example.handsonweek3.services.AnimeCharacterResult
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL


class AnimeCharacterResultAdapter(
    initialItems: List<AnimeCharacterResult>
) : RecyclerView.Adapter<ItemImageNameDescriptionViewHolder>() {

    private var items: List<AnimeCharacterResult> = initialItems

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<AnimeCharacterResult>) {
        items = newItems
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemImageNameDescriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return ItemImageNameDescriptionViewHolder(
            inflater.inflate(
                R.layout.item_image_name_desc,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemImageNameDescriptionViewHolder, position: Int) {
        val item = items[position]

        // TODO: Remove AsyncTask call and use a coroutine to fetch the image in the correct Scope:Done

        val mainActivityJob: CompletableJob = Job()
        val coroutineScope: CoroutineScope = CoroutineScope(mainActivityJob + Dispatchers.IO)

        coroutineScope.launch {

            val bitmap = BitmapFactory.decodeStream(URL(item.imageUrl).content as InputStream)

            // Setting the downloaded image to the ImageView.
           // holder.image.post { holder.image.setImageBitmap(bitmap) }
            withContext(Dispatchers.Main){
                 holder.image.setImageBitmap(bitmap)

            }
        }
        /*
         doAsync {
             // Downloading the bitmap inside the task.
             val bitmap = BitmapFactory.decodeStream(URL(item.imageUrl).content as InputStream)

             // Setting the downloaded image to the ImageView.
             holder.image.post { holder.image.setImageBitmap(bitmap) }
         }*/

        holder.name.text = item.name
        holder.description.text = item.role
    }
}