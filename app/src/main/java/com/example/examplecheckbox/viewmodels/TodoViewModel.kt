package com.example.examplecheckbox.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.examplecheckbox.models.TodoItemModel

class TodoViewModel : ViewModel() {
    val mTodoItemLiveData = MutableLiveData<ArrayList<TodoItemModel>>()

    init {
        initData()
    }

    private fun initData() {
        val dataDummy = ArrayList<TodoItemModel>()
        listOf(
            "Viet Nam",
            "Han Quoc",
            "Nhat Ban",
            "Thai Lan",
            "Lao"
        ).forEachIndexed { index, item ->
            dataDummy.add(TodoItemModel(index, item, index % 2 == 0))
        }
        mTodoItemLiveData.value = dataDummy
    }

}