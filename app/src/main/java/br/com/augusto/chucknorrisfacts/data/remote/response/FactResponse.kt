package br.com.augusto.chucknorrisfacts.data.remote.response

data class FactResponse(
    var categories: List<String>,
    var value: String,
    var url: String
)