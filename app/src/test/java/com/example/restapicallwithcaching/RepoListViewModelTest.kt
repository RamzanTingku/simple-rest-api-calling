package com.example.restapicallwithcaching

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.restapicallwithcaching.data.local.GithubRepoDao
import com.example.restapicallwithcaching.data.model.GithubRepoResponse
import com.example.restapicallwithcaching.data.model.RepoItem
import com.example.restapicallwithcaching.data.remote.GithubApiService
import com.example.restapicallwithcaching.data.repository.CharacterRepository
import com.example.restapicallwithcaching.ui.list.RepoListViewModel
import com.example.restapicallwithcaching.utils.NetworkConnectionChecker.isNetworkAvailable
import com.example.restapicallwithcaching.utils.Resource
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import kotlinx.coroutines.Dispatchers
import retrofit2.Response

@ExperimentalCoroutinesApi
class RepoListViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: GithubApiService

    @Mock
    private lateinit var dao: GithubRepoDao

    @Mock
    private lateinit var context: Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        mockkStatic("com.example.restapicallwithcaching.utils")
        every {
            isNetworkAvailable(context)
        } returns true
        testCoroutineRule.runBlockingTest {
            var githubRepoResponse = GithubRepoResponse(ArrayList<RepoItem>())
            var response = Response<GithubRepoResponse>(null, response, null)

            `when`(api.getRepos("Android", 1)).thenReturn()
            `when`(dao.getAllRepos()).thenReturn(null)
        }

        val repository = CharacterRepository()

        testCoroutineRule.pauseDispatcher()

        val viewModel = RepoListViewModel()

        assertThat(viewModel.repos.value, `is`(Resource.loading()))

        testCoroutineRule.resumeDispatcher()

        assertThat(viewModel.repos.value, `is`(Resource.success(emptyList())))
    }

   /* @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(Mockito.anyInt())).thenReturn(errorMsg)
        mockkStatic("com.android.sample.tvmaze.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        testCoroutineRule.runBlockingTest {
            `when`(api.fetchShowList()).thenThrow(RuntimeException(""))
            `when`(dao.getShows()).thenReturn(emptyList())
        }
        val repository = ShowRepository(dao, api, context, Dispatchers.Main)

        testCoroutineRule.pauseDispatcher()

        val viewModel = MainViewModel(repository)

        assertThat(viewModel.stateFlow.value, `is`(Resource.loading()))

        testCoroutineRule.resumeDispatcher()

        assertThat(viewModel.stateFlow.value, `is`(Resource.error(errorMsg)))
    }

    @Test
    fun givenNetworkUnAvailable_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(Mockito.anyInt())).thenReturn(errorMsg)
        mockkStatic("com.android.sample.tvmaze.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns false
        testCoroutineRule.runBlockingTest {
            `when`(dao.getShows()).thenReturn(emptyList())
        }
        val repository = ShowRepository(dao, api, context, Dispatchers.Main)

        testCoroutineRule.pauseDispatcher()

        val viewModel = MainViewModel(repository)

        assertThat(viewModel.stateFlow.value, `is`(Resource.loading()))

        testCoroutineRule.resumeDispatcher()

        assertThat(viewModel.stateFlow.value, `is`(Resource.error(errorMsg)))
    }*/
}