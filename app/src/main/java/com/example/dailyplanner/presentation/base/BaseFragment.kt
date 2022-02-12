package com.example.dailyplanner.presentation.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<VB : ViewBinding, S : State, I : ViewIntent, A : ViewAction, VM : BaseViewModel<S, I, A>>(
    @LayoutRes layout: Int
) : Fragment(layout) {

    abstract val binding: VB
    abstract val viewModel: VM
    protected val navController by lazy { findNavController() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.state.observe(this) {
            render(it)
        }
        viewModel.actions.observe(this) {
            processAction(it)
        }
    }

    fun dispatchIntent(intent: I) {
        viewModel.sendIntent(intent)
    }

    abstract fun render(state: S)

    abstract fun processAction(action: A)
}