package co.designbuilder.presentation.ui.words

import co.designbuilder.presentation.model.WordViewModel

interface IWordsAdapter {
  fun setData(data: Collection<WordViewModel>)
  fun getData(): Collection<WordViewModel>
}