package co.designbuilder.presentation.ui.words

import co.designbuilder.domain.interactor.WordsRequestUseCase
import co.designbuilder.domain.model.Word
import co.designbuilder.presentation.mapper.WordViewModelDataMapper
import co.designbuilder.presentation.ui.words.WordsContract.View
import io.reactivex.observers.DisposableObserver

class WordsPresenter(private var wordsRequestUseCase: WordsRequestUseCase,
    private var wordViewModelDataMapper: WordViewModelDataMapper)
  : WordsContract.Presenter {
  private var view: View? = null

  override fun detachView() {
    this.view = null
  }

  override fun attachView(view: View) {
    this.view = view
  }

  override fun loadWords(category: String) {
    val param = WordsRequestUseCase.Params.forWordsRequest(category)

    wordsRequestUseCase.execute(WordsObserver(), param)
  }

  fun onResponse(wordList: Collection<Word>) {
    view?.setData(wordViewModelDataMapper.map(wordList))
    view?.showContent()
  }

  fun onFailure(e: Throwable) {
    view?.showError(e.message ?: "No message")

  }

  open inner class WordsObserver : DisposableObserver<Collection<Word>>() {

    override fun onNext(wordList: Collection<Word>) {
      this@WordsPresenter.onResponse(wordList)
    }

    override fun onComplete() {
      // no-op by default.
    }

    override fun onError(e: Throwable) {
      this@WordsPresenter.onFailure(e)
    }
  }

  fun ifViewAttached(block: Any.() -> Unit) {
    if (view != null) {
      Any().apply(block)
    }
  }

}
