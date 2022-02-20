package com.mosaleh.sparkbanking.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mosaleh.sparkbanking.data.Repository
import com.mosaleh.sparkbanking.model.Transaction
import com.mosaleh.sparkbanking.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendMoneyViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val _users: MutableLiveData<List<User>> = MutableLiveData()
    val users: LiveData<List<User>> = _users

    fun getUsers() {
        viewModelScope.launch {
            val data = repository.getAllUsers()
            _users.value = data
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch {
            repository.updateUser(user)
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }
}