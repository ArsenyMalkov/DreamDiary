package com.dreamdiary.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dreamdiary.Injection
import com.dreamdiary.R
import com.dreamdiary.persistance.Dream
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dream_item.view.*
import kotlinx.android.synthetic.main.main_fragment.*


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private val TAG = MainFragment::class.java.simpleName
    }

    private val viewModel by viewModels<MainViewModel> {
        Injection.provideViewModelFactory(requireContext())
    }

    private var fragmentToActivityListener: FragmentToActivityListener? = null

    interface FragmentToActivityListener {
        fun onAddDreamListener()
        fun onClickDreamListener()
    }

    private val dreamAdapter = DreamAdapter()
    private val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dreamRecyclerView.adapter = dreamAdapter
        dreamRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        fab.setOnClickListener {
            fragmentToActivityListener?.onAddDreamListener()
        }
    }

    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.dreamList.subscribe(dreamAdapter::submitList))
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentToActivityListener) {
            fragmentToActivityListener = context
            dreamAdapter.fragmentToActivityListener = fragmentToActivityListener
        } else {
            throw ClassCastException("$context must implement $TAG.OnAddDreamListener")
        }
    }
}

class DreamAdapter :
    PagedListAdapter<Dream, DreamViewHolder>(diffCallback) {

    var fragmentToActivityListener: MainFragment.FragmentToActivityListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder =
        DreamViewHolder(parent, fragmentToActivityListener)

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Dream>() {
            override fun areItemsTheSame(oldDream: Dream, newDream: Dream): Boolean =
                oldDream.id == newDream.id

            override fun areContentsTheSame(oldDream: Dream, newDream: Dream): Boolean =
                oldDream == newDream
        }
    }

}

class DreamViewHolder(
    parent: ViewGroup,
    private val fragmentToActivityListener: MainFragment.FragmentToActivityListener?
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.dream_item,
        parent,
        false
    )
), View.OnClickListener {

    init {
        itemView.setOnClickListener(this)
    }

    fun bindTo(dream: Dream?) {
        itemView.tvTitle.text = dream?.title
        itemView.tvDate.text = dream?.date.toString()
    }

    override fun onClick(v: View?) {
        fragmentToActivityListener?.onClickDreamListener()
    }
}
