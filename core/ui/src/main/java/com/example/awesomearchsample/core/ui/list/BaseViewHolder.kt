package com.example.awesomearchsample.core.ui.list

import android.content.Context
import android.content.res.Resources
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val ctx: Context = itemView.context
    val res: Resources = itemView.context.resources

    abstract fun bind(item: T)
}