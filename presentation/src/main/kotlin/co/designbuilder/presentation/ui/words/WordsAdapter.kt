package co.designbuilder.presentation.ui.words

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import co.designbuilder.presentation.R
import co.designbuilder.presentation.model.WordViewModel
import co.designbuilder.presentation.ui.Utility

class WordsAdapter internal constructor(private val context: Context) : PagerAdapter(), IWordsAdapter {

  private val inflater: LayoutInflater = LayoutInflater.from(context)

  private var wordList: Collection<WordViewModel> = listOf()

  override fun getCount(): Int {
    return wordList.size
  }

  override fun isViewFromObject(view: View, any: Any): Boolean {
    return view === any
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val word = (wordList as List)[position]

    val cardLayout = inflater.inflate(R.layout.item_word_card, container, false)

    bindView(cardLayout, word)

    container.addView(cardLayout, LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.MATCH_PARENT))

    return cardLayout
  }

  private fun bindView(view: View, word: WordViewModel) {

    val headImageView = view.findViewById<ImageView>(R.id.wordCardImage)

    Glide.with(context)
        .load(word.imgUrl)
        .apply(RequestOptions.centerCropTransform())
//        .apply(RequestOptions.placeholderOf(R.mipmap.ic_no_images))
        .into(headImageView)

    val titleText = view.findViewById<TextView>(R.id.wordCardTitle)
    val description = view.findViewById<TextView>(R.id.wordCardDescription)
    val time = view.findViewById<TextView>(R.id.wordCardNote)

    titleText.text = word.word
    description.text = word.desc
    time.text = word.word

    val shareBtn = view.findViewById<ImageButton>(R.id.wordCardShareBtn)

    shareBtn.setOnClickListener {
      Utility.startShare(context, title = "Share link through...",
          subject = "Check out " + word.desc + "!", text = word.word)
    }

    view.setOnClickListener {

    }
  }

  override fun destroyItem(container: ViewGroup, position: Int, any: Any) {
    container.removeView(any as View)
  }

  override fun setData(data: Collection<WordViewModel>) {
    this.wordList = data
    notifyDataSetChanged()
  }

  override fun getData() = wordList

}
