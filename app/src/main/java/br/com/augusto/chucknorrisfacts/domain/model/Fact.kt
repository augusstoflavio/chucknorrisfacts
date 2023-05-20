package br.com.augusto.chucknorrisfacts.domain.model

data class Fact(
    var categories: List<String>,
    var value: String,
    var url: String,
)
