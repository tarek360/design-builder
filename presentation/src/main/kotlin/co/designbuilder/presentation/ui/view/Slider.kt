package co.designbuilder.presentation.ui.view

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

open class Slider(context: Context, attrs: AttributeSet? = null) : ViewPager(context, attrs) {

  var isDisabled: Boolean = false

  init {
    this.offscreenPageLimit = 2
    this.clipChildren = false
    this.clipToPadding = false
  }

  override fun onTouchEvent(event: MotionEvent): Boolean {
    return !this.isDisabled && super.onTouchEvent(event)
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    return !this.isDisabled && super.onInterceptTouchEvent(event)
  }
}
