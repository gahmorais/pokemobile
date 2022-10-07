package br.com.gabrielmorais.pokemobile.network

import br.com.gabrielmorais.pokemobile.models.Pokemon
import br.com.gabrielmorais.pokemobile.models.Resource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val retrofit = Retrofit
    .Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()


interface PokemonServiceApi {
    @GET("pokemon")
    suspend fun pokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 6
    ): Resource

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: String
    ): Pokemon
}

object PokemonApi {
    val retrofitService: PokemonServiceApi by lazy {
        retrofit.create(PokemonServiceApi::class.java)
    }
}
