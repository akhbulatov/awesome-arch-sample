package com.example.awesomearchsample.feature.search.navigation

import androidx.navigation3.runtime.NavKey
import com.example.awesomearchsample.feature.search.SearchRoute

fun ((NavKey) -> Unit).navigateToSearch() {
    this(SearchRoute)
}
