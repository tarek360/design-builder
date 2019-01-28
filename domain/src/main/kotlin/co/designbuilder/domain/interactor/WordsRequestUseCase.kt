package co.designbuilder.domain.interactor

import co.designbuilder.domain.model.Word
import co.designbuilder.domain.repository.WordsRepository
import co.designbuilder.domain.rx.ExecutionScheduler
import io.reactivex.Observable
import javax.inject.Inject

open class WordsRequestUseCase
@Inject constructor(
        private val wordsRepository: WordsRepository,
        executionScheduler: ExecutionScheduler)
    : UseCase<Collection<Word>, WordsRequestUseCase.Params>(executionScheduler) {

    override fun buildUseCaseObservable(params: WordsRequestUseCase.Params): Observable<Collection<Word>> {
        return wordsRepository.getWords(params.query)
    }

    class Params private constructor(val query: String) {
        companion object {

            fun forWordsRequest(query: String): Params {
                return Params(query)
            }
        }
    }
}