package br.com.augusto.chucknorrisfacts.modules.fact

import android.content.Context
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.FactRepository
import br.com.augusto.chucknorrisfacts.modules.fact.data.repository.IFactRepository
import br.com.augusto.chucknorrisfacts.modules.fact.service.FactService
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.FactsViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class FactModule: ModuleInterface {

    override fun getKoinModule(context: Context): Module? {

        return module {
            single<FactService> {
                val retrofit = get() as Retrofit
                retrofit.create(FactService::class.java)
            }

            single<IFactRepository> {
                FactRepository(get())
            }

            viewModel {
                FactsViewModel(get(), CompositeDisposable())
            }
        }
    }
}