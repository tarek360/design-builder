package co.designbuilder.presentation.ui.view

import android.content.Context
import android.graphics.Point
import android.os.Build
import android.os.Build.VERSION_CODES
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import co.designbuilder.presentation.model.WordViewModel
import co.designbuilder.presentation.ui.slider.SliderTransformer
import co.designbuilder.presentation.ui.words.IWordsAdapter

class SliderContainer
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null)
  : FrameLayout(context, attrs), ViewPager.OnPageChangeListener {

  private var adapter: IWordsAdapter? = null
  private var needsRedraw: Boolean = false
  private var slider: Slider
  private val center = Point()
  private val initialTouch = Point()

  init {
    clipChildren = false
    clipToPadding = false

    if (Build.VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
      setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    slider = Slider(context)

    val layoutParams = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
    this.addView(slider, 0, layoutParams)
    slider.setPageTransformer(false, SliderTransformer())
  }

  fun setAdapter(adapter: IWordsAdapter) {
    this.adapter = adapter
  }

  fun setData(wordList: Collection<WordViewModel>) {
    this.adapter?.setData(wordList)
    slider.adapter = adapter as? PagerAdapter
  }

  fun getCurrentItem() = slider.currentItem

  fun getItemsCount() = adapter?.getData()?.size

  override fun onFinishInflate() {
    super.onFinishInflate()
    slider = getChildAt(0) as Slider
    slider.addOnPageChangeListener(this)
    slider.setPageTransformer(false, SliderTransformer())
  }

  override fun onTouchEvent(ev: MotionEvent): Boolean {
    // Capture any touches not already handled by the ViewPager
    // to implement scrolling from a touch outside the pager bounds.
    when (ev.action) {
      MotionEvent.ACTION_DOWN -> {
        initialTouch.x = ev.x.toInt()
        initialTouch.y = ev.y.toInt()
        this.performClick()
        ev.offsetLocation((center.x - initialTouch.x).toFloat(),
            (center.y - initialTouch.y).toFloat())
      }
      MotionEvent.ACTION_UP -> {
        this.performClick()
        ev.offsetLocation((center.x - initialTouch.x).toFloat(),
            (center.y - initialTouch.y).toFloat())
      }
      else -> ev.offsetLocation((center.x - initialTouch.x).toFloat(),
          (center.y - initialTouch.y).toFloat())
    }
    return slider.dispatchTouchEvent(ev)
  }

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    if (needsRedraw) invalidate()
  }

  override fun onPageSelected(position: Int) {

  }

  override fun onPageScrollStateChanged(state: Int) {
    needsRedraw = state != ViewPager.SCROLL_STATE_IDLE
  }

  fun addOnPageChangeListener(onPageChangeListener: SimpleOnPageChangeListener) {
    slider.addOnPageChangeListener(onPageChangeListener)
  }
}
