package br.com.gabrielmorais.pokemobile.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import br.com.gabrielmorais.pokemobile.PokemonPagingSource
import br.com.gabrielmorais.pokemobile.models.Item
import br.com.gabrielmorais.pokemobile.network.PokemonApi

class PokemonListViewModel : ViewModel() {

    private val _item: MutableLiveData<Item> = MutableLiveData()
    val item: LiveData<Item> = _item

    val flow = Pager(PagingConfig(pageSize = 20)) {
        PokemonPagingSource(PokemonApi)
    }.flow.cachedIn(viewModelScope)

    fun onItemClicked(item: Item) {
        _item.value = item
    }
}