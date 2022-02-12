package com.example.dailyplanner.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<VB: ViewBinding,S: State, I: ViewIntent, A: ViewAction, VM: BaseViewModel<S,I,A>>(@LayoutRes contentLayoutId: Int): AppCompatActivity(contentLayoutId) {
    abstract val binding: VB
    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this){
            render(it)
        }
        viewModel.actions.observe(this){
            processAction(it)
        }
    }

    fun dispatchIntent(intent: I){
        viewModel.sendIntent(intent)
    }

    abstract fun render(state: S)

    abstract fun processAction(action: A)
}