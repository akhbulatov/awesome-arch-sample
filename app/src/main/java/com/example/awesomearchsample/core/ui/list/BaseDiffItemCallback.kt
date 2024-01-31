package com.example.awesomearchsample.core.ui.list

import androidx.recyclerview.widget.DiffUtil

abstract class BaseDiffItemCallback<T : Any> : DiffUtil.ItemCallback<T>()