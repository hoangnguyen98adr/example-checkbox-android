package com.example.examplecheckbox.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.examplecheckbox.R
import com.example.examplecheckbox.adapter.TodoItemAdapter
import com.example.examplecheckbox.viewmodels.TodoViewModel
import kotlinx.android.synthetic.main.fragment_home_screen.*

class HomeScreen : Fragment(), View.OnClickListener {
    private val mTodoItemAdapter = TodoItemAdapter()
    private val mTodoViewModel = TodoViewModel()
    private var mIsCheckFirst: Boolean = false
    private var mIsCheckSecond: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initObserver()
        return inflater.inflate(R.layout.fragment_home_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        initListener()
    }

    private fun initListener() {
        cbTodoAll.setOnClickListener(this)
        cbFist.setOnClickListener(this)
        cbSecond.setOnClickListener(this)
        mTodoItemAdapter.setCallback {
            cbTodoAll.isChecked = it
        }
    }

    private fun setupRecyclerView() {
        rvContainerTodo.apply {
            adapter = mTodoItemAdapter
            layoutManager = GridLayoutManager(activity, GRID_SPAN_COUNT)
        }
    }

    private fun initObserver() {
        mTodoViewModel.mTodoItemLiveData.observe(viewLifecycleOwner, Observer {
            mTodoItemAdapter.setList(it)
        })
    }

    companion object {
        private const val GRID_SPAN_COUNT = 2
    }

    override fun onClick(v: View) {
        when (v) {
            cbTodoAll -> {
                val cbAll = cbTodoAll.isChecked
                mTodoItemAdapter.setSelectedAll(cbAll)
                mTodoItemAdapter.mIsFocus = true
                cbFist.isChecked = cbAll
                cbSecond.isChecked = cbAll
            }
            cbFist -> {
                setCheckboxAll()
            }
            cbSecond -> {
                setCheckboxAll()
            }
        }
    }

    private fun setCheckboxAll() {
        mIsCheckFirst = cbFist.isChecked
        mIsCheckSecond = cbSecond.isChecked
        cbTodoAll.isChecked = mIsCheckFirst && mIsCheckSecond
    }
}