package br.com.gabrielmorais.pokemobile.models

data class Resource(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Item>
)