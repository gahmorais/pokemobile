package br.com.gabrielmorais.pokemobile.models

import com.google.gson.annotations.SerializedName

data class Pokemon(val sprites: Sprites, val species: Species)

data class Sprites(val other: Other)

data class Other(@SerializedName("official-artwork") val officialArtWork: OfficialArtWork)

data class OfficialArtWork(@SerializedName("front_default") val frontDefault: String)

data class Species(val name: String, val url: String)
