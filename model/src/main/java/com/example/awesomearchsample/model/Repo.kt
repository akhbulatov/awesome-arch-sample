package com.example.awesomearchsample.model

data class Repo(
    val id: Long,
    val name: String,
    val author: String,
    val description: String?,
    val inFavorites: Boolean = false
)

fun List<Repo>.updatedByToggleInFavorites(repoBy: Repo): List<Repo> {
    return this.map { repo ->
        if (repo.id == repoBy.id) {
            repo.copy(
                inFavorites = !repo.inFavorites
            )
        } else {
            repo
        }
    }
}