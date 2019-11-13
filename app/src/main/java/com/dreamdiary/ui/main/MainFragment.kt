package com.dreamdiary.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dreamdiary.Injection
import com.dreamdiary.R
import com.dreamdiary.persistance.Dream
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.dream_item.view.*
import kotlinx.android.synthetic.main.main_fragment.*
import android.content.Context


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
        private val TAG = MainFragment::class.java.simpleName
    }

    private val viewModel by viewModels<MainViewModel> {
        Injection.provideViewModelFactory(requireContext())
    }

    private val dreamAdapter = DreamAdapter()

    private val disposable = CompositeDisposable()

    private var listener: OnAddDreamListener? = null

    interface OnAddDreamListener {
        fun onAddDreamListener()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dreamRecyclerView.adapter = dreamAdapter
        fab.setOnClickListener {
            listener?.onAddDreamListener()
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
        if (context is OnAddDreamListener) {
            listener = context
        } else {
            throw ClassCastException("$context must implement $TAG.OnAddDreamListener")
        }
    }

    class DreamAdapter : PagedListAdapter<Dream, DreamAdapter.DreamViewHolder>(DIFF_CALLBACK) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.dream_item, parent, false)
            return DreamViewHolder(v)
        }

        override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
            val dream: Dream? = getItem(position)
            holder.bindTo(dream)
        }

        companion object {
            private val DIFF_CALLBACK = object :
                DiffUtil.ItemCallback<Dream>() {
                override fun areItemsTheSame(
                    oldDream: Dream,
                    newDream: Dream
                ) = oldDream.id == newDream.id

                override fun areContentsTheSame(
                    oldDream: Dream,
                    newDream: Dream
                ) = oldDream == newDream
            }
        }

        class DreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bindTo(dream: Dream?) {
                itemView.tv_title.text = dream?.title ?: "Not Found"
            }
        }
    }

}
