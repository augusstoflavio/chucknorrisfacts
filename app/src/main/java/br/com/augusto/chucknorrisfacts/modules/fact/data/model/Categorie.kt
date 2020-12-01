package br.com.augusto.chucknorrisfacts.modules.fact.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Categorie: RealmObject() {
    @PrimaryKey
    var name: String? = null
}