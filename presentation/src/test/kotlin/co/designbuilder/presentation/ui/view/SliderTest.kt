package co.designbuilder.presentation.ui.view

import android.view.MotionEvent
import co.designbuilder.presentation.RobolectricBase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.robolectric.RuntimeEnvironment

class SliderTest : RobolectricBase() {

    private lateinit var slider: Slider

    @Before
    fun setUp() {
        slider = Slider(RuntimeEnvironment.application)
    }

    @Test
    fun init() {
        assertEquals(slider.offscreenPageLimit, 2)
        assertEquals(slider.clipChildren, false)
        assertEquals(slider.clipToPadding, false)
    }

    @Test
    fun setDisabled() {
        slider.isDisabled = true

        val motionEvent = mock(MotionEvent::class.java)
        assertEquals(slider.onTouchEvent(motionEvent), false)
        assertEquals(slider.onInterceptTouchEvent(motionEvent), false)
    }
}