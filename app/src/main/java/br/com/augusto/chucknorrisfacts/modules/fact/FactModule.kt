package br.com.augusto.chucknorrisfacts.modules.fact

import android.content.Context
import br.com.augusto.chucknorrisfacts.app.module.ModuleInterface
import br.com.augusto.chucknorrisfacts.data.local.database.dao.CategoryDaoImpl
import br.com.augusto.chucknorrisfacts.data.local.database.dao.CategoryDao
import br.com.augusto.chucknorrisfacts.data.local.database.dao.SearchDao
import br.com.augusto.chucknorrisfacts.data.local.database.dao.SearchDaoImpl
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepositoryImpl
import br.com.augusto.chucknorrisfacts.domain.repository.FactRepository
import br.com.augusto.chucknorrisfacts.data.remote.FactService
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.FactsViewModel
import br.com.augusto.chucknorrisfacts.modules.fact.ui.viewModel.SearchFactsViewModel
import io.reactivex.disposables.CompositeDisposable
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

class FactModule : ModuleInterface {

    override fun getKoinModule(context: Context): Module? {
        return module {
            single<FactService> {
                val retrofit = get() as Retrofit
                retrofit.create(FactService::class.java)
            }

            single<SearchDao> {
                SearchDaoImpl()
            }

            single<CategoryDao> {
                CategoryDaoImpl()
            }

            single<FactRepository> {
                FactRepositoryImpl(get(), get(), get())
            }

            viewModel {
                FactsViewModel(get(), get())
            }

            viewModel {
                SearchFactsViewModel(get(), CompositeDisposable())
            }
        }
    }
}
