package com.goget.ui.userlist.view

import com.goget.logic.dataaccess.endpoint.UserEndpointProvider
import com.goget.ui.userslist.viewmodel.UserListViewModel
import com.goget.utils.RxTrampolineSchedulers
import com.goget.utils.UserEndpointProviderModelCreator
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {
    @get:Rule
    val rxTrampolineSchedulers = RxTrampolineSchedulers()


    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var endpointProvider: UserEndpointProvider


    @InjectMocks
    private lateinit var viewModel: UserListViewModel



    @Test
    fun load_data_success() {
        val firstPage = UserEndpointProviderModelCreator.listUsersFirstPage()
        val lastPage = UserEndpointProviderModelCreator.listUsersLastPage()
        Mockito.`when`(endpointProvider.listUsers(1)).thenReturn(Single.just(firstPage))
        Mockito.`when`(endpointProvider.listUsers(76)).thenReturn(Single.just(lastPage))

        viewModel.loadData()

        verify(endpointProvider).listUsers(1)
        verify(endpointProvider).listUsers(76)

        assert(viewModel.usersPage.get()?.meta?.pagination?.page == 76)
        assert(viewModel.isLoading.get() == false)
        assert(viewModel.isError.get() == false)
    }

    @Test
    fun load_data_fail() {
        Mockito.`when`(endpointProvider.listUsers(1)).thenReturn(Single.error(RuntimeException()))

        viewModel.loadData()

        assert(viewModel.isLoading.get() == false)
        assert(viewModel.isError.get() == true)
    }


    @Test
    fun add_user_success() {
        val firstPage = UserEndpointProviderModelCreator.listUsersFirstPage()
        val lastPage = UserEndpointProviderModelCreator.listUsersLastPage()
        Mockito.`when`(endpointProvider.addUser(any())).thenReturn(Single.just(UserEndpointProviderModelCreator.addUser()))
        Mockito.`when`(endpointProvider.listUsers(1)).thenReturn(Single.just(firstPage))
        Mockito.`when`(endpointProvider.listUsers(76)).thenReturn(Single.just(lastPage))

        viewModel.addUser()

        verify(endpointProvider).addUser(any())
        verify(endpointProvider).listUsers(1)
        verify(endpointProvider).listUsers(76)

        assert(viewModel.isAddDialogVisible.get() == false)
        assert(viewModel.isLoading.get() == false)
        assert(viewModel.isError.get() == false)
    }

    @Test
    fun add_user_fail() {
        Mockito.`when`(endpointProvider.addUser(any())).thenReturn(Single.error(RuntimeException()))

        viewModel.addUser()

        verify(endpointProvider).addUser(any())

        assert(viewModel.isError.get() == true)
    }


    @Test
    fun delete_user_success() {
        val userToDelete = UserEndpointProviderModelCreator.listUsersLastPage().data?.get(0)
        val firstPage = UserEndpointProviderModelCreator.listUsersFirstPage()
        val lastPage = UserEndpointProviderModelCreator.listUsersLastPage()
        viewModel.userToDelete = userToDelete

        Mockito.`when`(endpointProvider.listUsers(1)).thenReturn(Single.just(firstPage))
        Mockito.`when`(endpointProvider.listUsers(76)).thenReturn(Single.just(lastPage))
        Mockito.`when`(endpointProvider.deleteUser(viewModel.userToDelete?.id ?: -1)).thenReturn(Single.just(UserEndpointProviderModelCreator.deleteUser()))

        viewModel.deleteUser()

        verify(endpointProvider).deleteUser(userToDelete?.id ?: -1)

        assert(viewModel.isLoading.get() == false)
        assert(viewModel.isError.get() == false)
        assert(viewModel.isDeleteDialogVisible.get() == false)
        assert(viewModel.userToDelete == null)

        verify(endpointProvider).listUsers(1)
        verify(endpointProvider).listUsers(76)
    }

    @Test
    fun delete_user_fail() {
        val userToDelete = UserEndpointProviderModelCreator.listUsersLastPage().data?.get(0)
        viewModel.userToDelete = userToDelete

        Mockito.`when`(endpointProvider.deleteUser(any())).thenReturn(Single.error(RuntimeException()))

        viewModel.deleteUser()

        verify(endpointProvider).deleteUser(any())

        assert(viewModel.isError.get() == true)
        assert(viewModel.isDeleteDialogVisible.get() == false)
    }



    @Test
    fun clear_resources() {
        viewModel.clear()

        verify(compositeDisposable).clear()
    }

}