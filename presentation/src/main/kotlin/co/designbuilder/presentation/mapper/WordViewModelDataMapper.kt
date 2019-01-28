package co.designbuilder.presentation.mapper

import co.designbuilder.data.entity.mapper.Mapper
import co.designbuilder.domain.model.Word
import co.designbuilder.presentation.model.WordViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class WordViewModelDataMapper @Inject constructor() : Mapper<Word, WordViewModel>() {

  override fun map(src: Word): WordViewModel = WordViewModel(
      src.id,
      src.word,
      src.desc,
      src.imgUrl
  )

  override fun mapBack(dst: WordViewModel): Word = Word(
      dst.id,
      dst.word,
      dst.desc,
      dst.imgUrl
  )
}
