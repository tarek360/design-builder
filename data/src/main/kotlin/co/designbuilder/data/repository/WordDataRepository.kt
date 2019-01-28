package co.designbuilder.data.repository

import co.designbuilder.data.entity.mapper.WordEntityDataMapper
import co.designbuilder.data.repository.datasource.WordDataStoreFactory
import co.designbuilder.domain.model.Word
import co.designbuilder.domain.repository.WordsRepository
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WordDataRepository
@Inject constructor(private val wordDataStoreFactory: WordDataStoreFactory,
    private val wordEntityDataMapper: WordEntityDataMapper) : WordsRepository {

  override fun getWords(query: String): Observable<Collection<Word>> {
    return wordDataStoreFactory.createCloudDataStore().fetchWords(query).map {
      wordEntityDataMapper.map(it.words)
    }
  }
}
