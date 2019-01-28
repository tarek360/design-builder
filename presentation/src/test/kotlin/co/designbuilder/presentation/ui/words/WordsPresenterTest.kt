package co.designbuilder.presentation.ui.words

import co.designbuilder.domain.interactor.WordsRequestUseCase
import co.designbuilder.domain.repository.WordsRepository
import co.designbuilder.domain.rx.ExecutionScheduler
import co.designbuilder.presentation.mapper.WordViewModelDataMapper
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations

class WordsPresenterTest {

    @Mock
    private lateinit var wordsViewMock: WordsContract.View

    @Mock
    private lateinit var executionScheduler: ExecutionScheduler

    @Mock
    private lateinit var wordsRepository: WordsRepository

    @InjectMocks
    private lateinit var wordsRequestUseCase: WordsRequestUseCase

    @Mock
    private lateinit var wordViewModelDataMapper: WordViewModelDataMapper

    private lateinit var wordsPresenter: WordsPresenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        wordsPresenter = WordsPresenter(wordsRequestUseCase, wordViewModelDataMapper)
        wordsPresenter.attachView(wordsViewMock)
    }

    @Test
    fun testOnResponse() {
        wordsPresenter.onResponse(ArrayList())
        inOrder(wordsViewMock) {
            verify(wordsViewMock).setData(any())
            verify(wordsViewMock).showContent()
        }
    }

    @Test
    fun testOnFailure() {
        wordsPresenter.onFailure(Throwable())
        verify(wordsViewMock).showError(any())
    }

    @Test
    fun testOnResponseWithNullView() {
        wordsPresenter.detachView()

        wordsPresenter.onResponse(ArrayList())
        verify(wordsViewMock, never()).setData(any())
        verify(wordsViewMock, never()).showContent()
    }

    @Test
    fun testOnFailureWithNullView() {
        wordsPresenter.detachView()

        wordsPresenter.onFailure(Throwable())
        verify(wordsViewMock, never()).showError(any())
    }
}
