package com.example.awesomearchsample.feature.repo.repos

import com.example.awesomearchsample.domain.repo.model.Repo

private fun List<Repo>.updateRepo(
    repoId: Long,
    transform: (Repo) -> Repo
): List<Repo> =
    map { repo ->
        if (repo.id == repoId) transform(repo) else repo
    }

internal fun List<Repo>.withFavoriteToggled(repoId: Long): List<Repo> =
    updateRepo(repoId) { repo ->
        repo.copy(inFavorites = !repo.inFavorites)
    }
