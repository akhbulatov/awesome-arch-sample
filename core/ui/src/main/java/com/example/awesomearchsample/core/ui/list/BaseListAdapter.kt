package com.example.awesomearchsample.core.ui.list

import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T : Any, VH : BaseViewHolder<T>>(
    diffCallback: BaseDiffItemCallback<T>,
) : ListAdapter<T, VH>(diffCallback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}