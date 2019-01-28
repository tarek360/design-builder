package co.designbuilder.domain.repository

import io.reactivex.Observable
import co.designbuilder.domain.model.Word

/**
 * Interface that represents a Repository for getting [Word] related data.
 */
interface WordsRepository {
    /**
     * Get an [Observable] which will emit a List of [Word].
     */
    fun getWords(query: String): Observable<Collection<Word>>
}

