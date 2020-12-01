package br.com.augusto.chucknorrisfacts.modules.fact

import android.content.Context
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import br.com.augusto.chucknorrisfacts.modules.fact.service.FactService
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class FactModule: ModuleInterface {
    override fun getKoinModule(context: Context): Module? {
        return module {
            single<FactService> {
                var retrofit = get() as Retrofit
                retrofit.create(FactService::class.java)
            }
        }
    }
}