package co.designbuilder.data.repository.datasource

import co.designbuilder.data.net.AppApi
import co.designbuilder.data.net.WordRequest
import co.designbuilder.data.net.WordResponse

import io.reactivex.Observable

/**
 * [WordDataStore] implementation based on connections to the api (Cloud).
 */
internal class WordCloudDataStore
/**
 * Construct a [WordDataStore] based on connections to the api (Cloud).
 *
 * @param restApi The [AppApi] implementation to use.
 */
(private val restApi: AppApi) : WordDataStore {

    override fun fetchWords(query: String): Observable<WordResponse> {
        return this.restApi.fetchWords(WordRequest(query))
    }
}
