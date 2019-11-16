package com.dreamdiary.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dreamdiary.Injection
import com.dreamdiary.R
import com.dreamdiary.persistance.Dream
import kotlinx.android.synthetic.main.fragment_add_dream.*

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

        btnAdd.setOnClickListener {
            val dream = Dream(
                title = etTitle.text.toString(),
                date = etDate.text.toString().toLong(),
                content = etContent.text.toString()
            )
            viewModel.updateDream(dream).subscribe { requireActivity().supportFragmentManager.popBackStack() }
//            requireActivity().supportFragmentManager.popBackStack()
//            Toast.makeText(requireContext(), "New Dream was Added", Toast.LENGTH_SHORT).show()
        }
    }

}
