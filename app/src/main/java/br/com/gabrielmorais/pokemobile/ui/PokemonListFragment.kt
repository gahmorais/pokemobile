package br.com.gabrielmorais.pokemobile.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import br.com.gabrielmorais.pokemobile.R
import br.com.gabrielmorais.pokemobile.databinding.FragmentPokemonListBinding
import br.com.gabrielmorais.pokemobile.models.Resource
import br.com.gabrielmorais.pokemobile.ui.viewmodel.PokemonListViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Response

class PokemonListFragment : Fragment() {
    private val viewModel: PokemonListViewModel by lazy {
        ViewModelProvider(this)[PokemonListViewModel::class.java]
    }

    private lateinit var binding: FragmentPokemonListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPokemonListBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.pokemonList.adapter = ItemListAdapter(ItemListener { item ->
            viewModel.onItemClicked(item)
        })

        val adapter = binding.pokemonList.adapter as ItemListAdapter

        lifecycleScope.launch {
            viewModel.flow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }

            adapter.loadStateFlow.collectLatest { loadingState ->

            }
        }

        return binding.root
    }

}