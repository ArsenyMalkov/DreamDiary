package com.dreamdiary.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamdiary.Injection
import com.dreamdiary.R

class AddDreamFragment : Fragment() {

    companion object {
        fun newInstance() = AddDreamFragment()
        private val TAG = AddDreamFragment::class.java.simpleName
    }

    private val viewModel by viewModels<MainViewModel> {
        Injection.provideViewModelFactory(requireContext())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
