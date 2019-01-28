package co.designbuilder.presentation.ui.slider

import android.support.v4.view.ViewPager
import android.view.View

class SliderTransformer : ViewPager.PageTransformer {

  override fun transformPage(view: View, position: Float) {
    when {
      position < -1 -> {
        view.alpha = INACTIVE_ALPHA
        view.scaleX = INACTIVE_SCALE
        view.scaleY = INACTIVE_SCALE
      }
      position <= 1 -> {
        val scale = INACTIVE_SCALE + (1 - INACTIVE_SCALE) * (1 - Math.abs(position))
        val alpha = INACTIVE_ALPHA + (1 - INACTIVE_ALPHA) * (1 - Math.abs(position))
        view.scaleX = scale
        view.scaleY = scale
        view.alpha = alpha
      }
      else -> {
        view.alpha = INACTIVE_ALPHA
        view.scaleX = INACTIVE_SCALE
        view.scaleY = INACTIVE_SCALE
      }
    }
  }

  companion object {
    private const val INACTIVE_SCALE = 0.9f
    private const val INACTIVE_ALPHA = 0.7f
  }
}
