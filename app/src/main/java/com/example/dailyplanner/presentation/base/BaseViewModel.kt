package com.example.dailyplanner.presentation.base


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<S : State, I:ViewIntent, A: ViewAction> : ViewModel() {
    protected val mState: MutableLiveData<S> = MutableLiveData()
    val state: LiveData<S>
        get() = mState
    protected val mActions: SingleLiveEvent<A> = SingleLiveEvent()
    val actions: LiveData<A>
        get() = mActions

    fun sendIntent(intent: I){
        handleIntent(intent)
    }

    protected abstract fun handleIntent(intent: I)

}