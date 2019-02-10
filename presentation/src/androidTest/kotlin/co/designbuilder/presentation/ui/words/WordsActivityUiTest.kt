package co.designbuilder.presentation.ui.words

import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WordsActivityUiTest {

  @Rule
  @JvmField
  val activityTestRule = ActivityTestRule(WordsActivity::class.java)

  @Test
  @Throws(InterruptedException::class)
  fun testRecyclerViewDisplay() {

    WordsPage
        .here()
        .assertWordCardTitle("Football")
        .swipeToRight()
        .assertWordCardTitle("Ball")
        .swipeToRight()
        .assertWordCardTitle("Stadium")
  }

}
