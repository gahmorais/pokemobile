package br.com.gabrielmorais.pokemobile.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.gabrielmorais.pokemobile.R
import br.com.gabrielmorais.pokemobile.databinding.FragmentPokemonListBinding
import br.com.gabrielmorais.pokemobile.ui.viewmodel.PokemonListViewModel

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

        return binding.root
    }

}