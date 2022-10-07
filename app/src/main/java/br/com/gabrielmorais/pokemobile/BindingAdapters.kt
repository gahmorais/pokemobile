package br.com.gabrielmorais.pokemobile

import android.media.Image
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmorais.pokemobile.models.Item
import br.com.gabrielmorais.pokemobile.ui.ItemListAdapter
import coil.load

//@BindingAdapter("listData")
//fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
//    val adapter = recyclerView.adapter as ItemListAdapter
//    adapter.submitList(data)
//}

@BindingAdapter("imgUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}
