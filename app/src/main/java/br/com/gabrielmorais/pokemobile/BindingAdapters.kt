package br.com.gabrielmorais.pokemobile

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.gabrielmorais.pokemobile.models.Item
import br.com.gabrielmorais.pokemobile.ui.ItemListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Item>?) {
    val adapter = recyclerView.adapter as ItemListAdapter
    adapter.submitList(data)
}
