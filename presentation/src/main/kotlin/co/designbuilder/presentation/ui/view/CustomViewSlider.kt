package co.designbuilder.presentation.ui.view

import android.content.Context
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import co.designbuilder.presentation.R
import co.designbuilder.presentation.model.WordViewModel
import co.designbuilder.presentation.ui.words.IWordsAdapter

import kotlinx.android.synthetic.main.custom_view_slider_layout.view.sliderContainer
import kotlinx.android.synthetic.main.custom_view_slider_layout.view.sliderCounterText

class CustomViewSlider
@JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
  : LinearLayout(context, attrs, defStyleAttr) {

  init {
    View.inflate(context, R.layout.custom_view_slider_layout, this)
    orientation = VERTICAL
    sliderContainer.addOnPageChangeListener(object : SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        setSliderCounterPosition(position, sliderContainer.getItemsCount()!!)
      }
    })
  }

  fun setAdapter(adapter: IWordsAdapter) {
    sliderContainer.setAdapter(adapter)
  }

  fun setData(wordList: Collection<WordViewModel>) {
    sliderContainer.setData(wordList)
    setSliderCounterPosition(sliderContainer.getCurrentItem(), sliderContainer.getItemsCount()!!)
  }

  fun addOnPageChangeListener(onPageChangeListenerAdapter: SimpleOnPageChangeListener) {
    sliderContainer.addOnPageChangeListener(onPageChangeListenerAdapter)
  }

  fun setSliderCounterPosition(position: Int, total: Int) {
    sliderCounterText.text = context.getString(R.string.view_pager_indicator_text,
        position + 1, total)
  }
}
