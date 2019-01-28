package co.designbuilder.presentation.di

import co.designbuilder.data.entity.mapper.WordEntityDataMapper
import co.designbuilder.data.net.AppApi
import co.designbuilder.data.net.RemoteAppApi
import co.designbuilder.data.repository.WordDataRepository
import co.designbuilder.data.repository.datasource.WordDataStoreFactory
import co.designbuilder.domain.interactor.WordsRequestUseCase
import co.designbuilder.domain.repository.WordsRepository
import co.designbuilder.domain.rx.ExecutionScheduler
import co.designbuilder.presentation.mapper.WordViewModelDataMapper
import co.designbuilder.presentation.rx.AppSchedulerProvider
import co.designbuilder.presentation.ui.words.IWordsAdapter
import co.designbuilder.presentation.ui.words.WordsAdapter
import co.designbuilder.presentation.ui.words.WordsContract
import co.designbuilder.presentation.ui.words.WordsPresenter
import org.koin.dsl.module.module

val wordsActivityModule = module {
    single { AppSchedulerProvider() }
    single { WordDataStoreFactory() }
    single { WordViewModelDataMapper() }
    single { WordEntityDataMapper() }
    single<WordsRepository> { WordDataRepository(get(), get()) }
    single<AppApi> { RemoteAppApi() }
    factory<WordsContract.Presenter> { WordsPresenter(get(), get()) }
    factory {
        val appSchedulerProvider = get() as AppSchedulerProvider
        val executionScheduler = ExecutionScheduler(appSchedulerProvider.io(), appSchedulerProvider.main())
        WordsRequestUseCase(get(), executionScheduler)
    }
    factory<IWordsAdapter> { WordsAdapter(get()) }
}

val allModules = listOf(wordsActivityModule)