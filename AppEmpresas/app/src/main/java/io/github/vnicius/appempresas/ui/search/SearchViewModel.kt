package io.github.vnicius.appempresas.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.vnicius.appempresas.data.auth.AuthException
import io.github.vnicius.appempresas.data.model.Enterprise
import io.github.vnicius.appempresas.data.repository.enterprise.EnterpriseRepository
import io.github.vnicius.appempresas.util.RequestState
import io.github.vnicius.appempresas.util.RequestState.*
import kotlinx.coroutines.launch
import retrofit2.HttpException


class SearchViewModel(private val enterpriseRepository: EnterpriseRepository) : ViewModel() {

    private val mutableRequestState = MutableLiveData<RequestState>()
    private val mutableSearchResult = MutableLiveData<List<Enterprise>>()

    var requestState: LiveData<RequestState> = mutableRequestState
    var searchResult: LiveData<List<Enterprise>> = mutableSearchResult

    fun search(query: String) {
        viewModelScope.launch {
            try {
                mutableRequestState.postValue(LOADING)

                val result = enterpriseRepository.search(query)
                mutableSearchResult.postValue(result)

                mutableRequestState.postValue(SUCCESS)
            } catch (e: HttpException) {
                mutableRequestState.postValue(FAILED(AuthException.UnauthorizedException()))
            } catch (e: Exception) {
                mutableRequestState.postValue(FAILED(e))
            }
        }
    }
}