package com.example.awesomearchsample.feature.repo.repos.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.awesomearchsample.core.ui.designsystem.AppTheme
import com.example.awesomearchsample.domain.repo.model.Repo

const val REPOS_LIST_TAG = "repos_list"
const val REPO_ITEM_TAG_PREFIX = "repo_item_"

@Composable
internal fun RepoList(
    repos: List<Repo>,
    onRepoItemClick: (Repo) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .testTag(REPOS_LIST_TAG)
    ) {
        items(
            items = repos,
            key = { repo -> repo.id }
        ) { repo ->
            RepoItem(
                repo = repo,
                onRepoItemClick = onRepoItemClick
            )
        }
    }
}

@Composable
private fun RepoItem(
    repo: Repo,
    onRepoItemClick: (Repo) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onRepoItemClick(repo) }
            .padding(15.dp)
            .testTag(REPO_ITEM_TAG_PREFIX + repo.id)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Name
            Text(
                text = repo.name,
                modifier = Modifier
                    .width(0.dp)
                    .weight(1f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.width(15.dp))

            // Author
            Text(
                text = repo.author,
                color = Color(0xDE000000),
                fontSize = 12.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = repo.description.orEmpty(),
            modifier = Modifier.fillMaxWidth(),
            color = Color(0x99000000)
        )
    }
}

//region Previews
@Preview(showBackground = true)
@Composable
private fun RepoListPreview() {
    AppTheme {
        RepoList(
            repos = RepoListPreviewData.list,
            onRepoItemClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepoItemPreview() {
    AppTheme {
        RepoItem(
            repo = RepoListPreviewData.item,
            onRepoItemClick = {}
        )
    }
}

private object RepoListPreviewData {
    val item = Repo(
        id = 0L,
        name = "AwesomeArchSample: 0",
        author = "akhbulatov",
        description = "Awesome open-source arch sample written in Kotlin"
    )

    val list = List(5) { index ->
        item.copy(
            id = index.toLong(),
            name = "AwesomeArchSample: $index"
        )
    }
}
//endregion
