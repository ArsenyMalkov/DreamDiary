package com.dreamdiary.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.dreamdiary.Injection
import com.dreamdiary.R
import io.reactivex.disposables.CompositeDisposable

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private val TAG = MainFragment::class.java.simpleName
    }

    private val viewModel by viewModels<MainViewModel> {
        Injection.provideViewModelFactory(requireContext())
    }

    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

}
