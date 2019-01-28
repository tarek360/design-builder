package co.designbuilder.data.repository.datasource

import co.designbuilder.data.net.WordResponse

import io.reactivex.Observable

/**
 * Interface that represents a data store from where data is retrieved.
 */
interface WordDataStore {
    /**
     * Get an [Observable] which will emit a [WordResponse].
     */
    fun fetchWords(query: String): Observable<WordResponse>
}
