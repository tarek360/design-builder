package co.designbuilder.presentation.ui.view

import android.widget.LinearLayout
import co.designbuilder.presentation.RobolectricBase
import kotlinx.android.synthetic.main.custom_view_slider_layout.view.sliderContainer
import kotlinx.android.synthetic.main.custom_view_slider_layout.view.sliderCounterText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.robolectric.RuntimeEnvironment

class CustomViewSliderTest : RobolectricBase() {

  private lateinit var view: CustomViewSlider

  @Before
  fun setUp() {
    view = CustomViewSlider(RuntimeEnvironment.application)
  }

  @Test
  fun init() {
    assertEquals(view.orientation, LinearLayout.VERTICAL)
    assertNotEquals(view.sliderContainer, null)
  }

  @Test
  fun setSliderCounterPosition() {
    view.setSliderCounterPosition(3, 10)
    assertEquals("4 / 10", view.sliderCounterText.text)
  }
}