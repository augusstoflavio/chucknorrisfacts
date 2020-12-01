package br.com.augusto.chucknorrisfacts.app.database

import io.realm.Realm
import io.realm.RealmConfiguration

class Database {

    companion object {
        fun getInstance(): Realm {
            val config = RealmConfiguration.Builder()
                .name("chuck_norris_facts.realm")
                .schemaVersion(1)
                .build()

            return Realm.getInstance(config)
        }
    }
}