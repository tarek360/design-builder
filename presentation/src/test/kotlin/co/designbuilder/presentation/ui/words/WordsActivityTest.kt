package co.designbuilder.presentation.ui.words

import android.widget.ProgressBar
import co.designbuilder.presentation.KoinRobolectricBase
import co.designbuilder.presentation.R
import co.designbuilder.presentation.model.WordViewModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext.startKoin
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RuntimeEnvironment

class WordsActivityTest : KoinRobolectricBase<WordsActivity>() {

    @Mock
    private lateinit var presenter: WordsContract.Presenter

    @Mock
    private lateinit var adapter: IWordsAdapter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        initKoin()
        val intent = WordsActivity.buildIntent(RuntimeEnvironment.application.applicationContext, "test")
        startActivity(WordsActivity::class.java, intent)
    }

    private fun initKoin() {
        val wordsActivityModule = module {
            factory { presenter }
            factory { adapter }
        }
        val testModules = listOf(wordsActivityModule)
        startKoin(testModules)
    }

    @Test
    fun assertViewsExist() {
        val loadingView = activity.findViewById<ProgressBar>(R.id.loadingView)
        assertNotNull("loadingView could not be found", loadingView)
    }

    @Test
    fun testLoadWords() {
        verify(presenter).attachView(any())
        verify(presenter).loadWords(any())
    }

    @Test
    fun checkIfDataSetToAdapter() {
        //Arrange
        val list = ArrayList<WordViewModel>()
        list.add(WordViewModel(2, "Name", "desc", "url"))

        //Act
        activity.setData(list)

        //Assert
        verify(adapter).setData(list)
    }

}
