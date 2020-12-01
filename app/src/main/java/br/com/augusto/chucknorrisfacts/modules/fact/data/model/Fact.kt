package br.com.augusto.chucknorrisfacts.modules.fact.data.model

data class Fact (
    var categories: List<String>,
    var value: String,
    var url: String
)