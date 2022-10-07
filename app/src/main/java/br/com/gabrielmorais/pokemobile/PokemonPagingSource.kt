package br.com.gabrielmorais.pokemobile

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.gabrielmorais.pokemobile.models.Item
import br.com.gabrielmorais.pokemobile.network.PokemonApi
import retrofit2.HttpException
import java.io.IOException

class PokemonPagingSource(private val backend: PokemonApi) :
    PagingSource<Int, Item>() {
    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response = backend.retrofitService.pokemons(offset = nextPageNumber)
            val responseWithImg = response.results.map {
                val data = backend.retrofitService.getPokemon(id = extractIdFrom(it.url))
                it.imgUrl = data.sprites.other.officialArtWork.frontDefault
                it
            }
            val next = extractPageFrom(response.next!!) + nextPageNumber
            LoadResult.Page(
                data = responseWithImg,
                prevKey = null,
                nextKey = next
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }

    private fun extractPageFrom(value: String): Int {
        return value.substringAfter("&limit=").toInt()
    }

    private fun extractIdFrom(value: String): String {
        return value.substringAfter("/pokemon/").substringBefore("/")
    }
}