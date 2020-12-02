package br.com.augusto.chucknorrisfacts.modules.fact.data.model

data class Fact (
    var categories: List<String>,
    var value: String,
    var url: String
) {
    fun isLongFact(): Boolean {
        return value.length > 80
    }
}