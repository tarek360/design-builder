package co.designbuilder.data.entity.mapper

import co.designbuilder.data.entity.WordEntity
import co.designbuilder.domain.model.Word
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Mapper class used to transform [WordEntity] (in the data layer) to [Word] in the
 * domain layer.
 */
@Singleton
class WordEntityDataMapper @Inject constructor() : Mapper<WordEntity, Word>() {

  override fun map(src: WordEntity): Word = Word(
      src.id,
      src.word,
      src.desc,
      src.imgUrl
  )

  override fun mapBack(dst: Word): WordEntity = WordEntity(
      dst.id,
      dst.word,
      dst.desc,
      dst.imgUrl
  )

}
