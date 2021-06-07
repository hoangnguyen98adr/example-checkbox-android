package com.example.examplecheckbox.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.examplecheckbox.R
import com.example.examplecheckbox.models.TodoItemModel
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoItemAdapter : RecyclerView.Adapter<TodoItemAdapter.TodoItemViewHolder>() {
    private var mListTodoItem = ArrayList<TodoItemModel>()
    private var mIsSelectedAll: Boolean = false
    var mIsFocus: Boolean = false
    private var mCallback: ((Boolean) -> Unit)? = null

    inner class TodoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun setList(list: ArrayList<TodoItemModel>) {
        mListTodoItem = list
        notifyDataSetChanged()
    }

    fun setSelectedAll(isSelected: Boolean) {
        mIsSelectedAll = isSelected
        notifyDataSetChanged()
    }

    fun setCallback(callback: (Boolean) -> Unit) {
        mCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount() = mListTodoItem.size

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val itemData = mListTodoItem[position]
        holder.itemView.apply {
            cbTodoItem.isChecked = itemData.isChecked
            cbTodoItem.text = itemData.name
            if (mIsFocus) cbTodoItem.isChecked = mIsSelectedAll
            itemData.isChecked = cbTodoItem.isChecked
            cbTodoItem.setOnClickListener {
                itemData.isChecked = cbTodoItem.isChecked
                mCallback?.invoke(setSelectedAll())
            }
        }
    }

    private fun setSelectedAll(): Boolean {
        var isSelected = true
        mListTodoItem.forEach {
            if (it.isChecked != isSelected) {
                isSelected = it.isChecked
                return isSelected
            }
        }
        return isSelected
    }
}