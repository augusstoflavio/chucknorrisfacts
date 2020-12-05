package br.com.augusto.chucknorrisfacts.modules.fact.data.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Search: RealmObject() {
    @PrimaryKey
    var name: String? = null
    var date: Date? = null
}