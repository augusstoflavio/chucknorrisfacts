package br.com.augusto.chucknorrisfacts.modules.fact.data.response

data class FactResponse(
    var categories: List<String>,
    var value: String,
    var url: String
)
