package co.designbuilder.presentation.ui.words

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import co.designbuilder.presentation.R
import co.designbuilder.presentation.model.WordViewModel
import kotlinx.android.synthetic.main.activity_words.customViewSlider
import kotlinx.android.synthetic.main.activity_words.errorTextView
import org.koin.android.ext.android.inject

class WordsActivity : AppCompatActivity(), WordsContract.View {

  companion object {
    fun buildIntent(baseContext: Context?, query: String): Intent {

      val intent = Intent(baseContext, WordsActivity::class.java)
      intent.putExtra(KEY_CATEGORY, query)
      return intent
    }

    const val KEY_CATEGORY = "category"
  }

  private val presenter: WordsContract.Presenter by inject()

  private val adapter: IWordsAdapter by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_words)

    customViewSlider.setAdapter(adapter)

    presenter.attachView(this)

    loadData()
  }

  override fun setData(data: Collection<WordViewModel>) {
    customViewSlider.setData(data)
  }

  private fun loadData() {
    presenter.loadWords(intent.getStringExtra(KEY_CATEGORY) ?: "")
  }

  override fun getData(): Collection<WordViewModel> = adapter.getData()

  override fun showContent() {

  }

  override fun showError(msg: String) {
    errorTextView.text = msg
  }

  override fun onDestroy() {
    super.onDestroy()
    presenter.detachView()
  }
}
