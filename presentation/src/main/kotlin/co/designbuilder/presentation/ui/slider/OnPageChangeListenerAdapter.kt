package co.designbuilder.presentation.ui.slider

import android.support.v4.view.ViewPager

abstract class OnPageChangeListenerAdapter : ViewPager.OnPageChangeListener {

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

  override fun onPageSelected(position: Int) {}

  override fun onPageScrollStateChanged(state: Int) {}
}
