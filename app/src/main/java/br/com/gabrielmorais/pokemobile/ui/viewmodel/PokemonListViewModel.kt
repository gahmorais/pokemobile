package br.com.gabrielmorais.pokemobile.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.gabrielmorais.pokemobile.models.Item
import br.com.gabrielmorais.pokemobile.network.PokemonApi
import kotlinx.coroutines.launch

class PokemonListViewModel : ViewModel() {
    private val TAG = "PokemonListViewModel"
    private val _pokemonList: MutableLiveData<List<Item>> = MutableLiveData()
    val pokemonList: LiveData<List<Item>> = _pokemonList
    private val _item: MutableLiveData<Item> = MutableLiveData()
    val item: LiveData<Item> = _item

    init {
        getPokemonList()
    }

    private fun getPokemonList() {
        viewModelScope.launch {
            val response = PokemonApi.retrofitService.pokemons()
            _pokemonList.value = response.results
        }
    }

    fun onItemClicked(item: Item) {
        _item.value = item
    }
}