package co.designbuilder.data.repository.datasource

import co.designbuilder.data.net.RemoteAppApi

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Factory that creates different implementations of [WordDataStore].
 */
@Singleton
class WordDataStoreFactory @Inject constructor() {

    /**
     * Create [WordDataStore] to retrieve data from the Cloud.
     */
    fun createCloudDataStore(): WordDataStore {
        val logaApi = RemoteAppApi()
        return WordCloudDataStore(logaApi)
    }
}
