package co.designbuilder.presentation.ui.words

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.swipeLeft
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import co.designbuilder.presentation.R.id
import org.hamcrest.Matchers.allOf

class WordsPage {

  companion object {
    fun here(): WordsPage {
      onView(withId(id.wordsContainer))
      return WordsPage()
    }
  }

  private val slider by lazy {
    onView(withId(id.sliderContainer))
  }

  fun swipeToRight(): WordsPage {
    slider.perform(swipeLeft())
    return this
  }

  fun assertWordCardTitle(title: String): WordsPage {
    onView(
        allOf(
            withId(id.wordCardTitle),
            isCompletelyDisplayed()
        ))
        .check(matches(withText(title)))
    return this
  }
}