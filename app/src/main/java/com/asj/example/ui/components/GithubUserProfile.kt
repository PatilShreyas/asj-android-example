package com.asj.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.asj.example.R
import com.asj.example.ui.theme.AppTheme

data class GithubUserUiModel(
    val username: String,
    val profileUrl: String,
    val type: String,
    val name: String,
    val companyName: String?,
    val location: String?,
    val bio: String?,
    val reposCount: Int,
    val followers: Int
)

@Composable
fun GithubUserProfile(user: GithubUserUiModel) {
    Card(
        Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            AsyncImage(
                model = user.profileUrl,
                contentDescription = "Github User",
                modifier = Modifier.size(128.dp),
                placeholder = painterResource(id = R.drawable.ic_person)
            )
            Text(
                text = user.username,
                style = MaterialTheme.typography.h6
            )
            Text(text = "Name: ${user.name}")
            Text(text = "Type: ${user.type}")

            val companyName = user.companyName
            if (companyName != null) {
                Text(text = "Company: $companyName")
            }

            val location = user.location
            if (location != null) {
                Text(text = "Location: $location")
            }

            Text(text = "Followers: ${user.followers}")
            Text(text = "Repositories: ${user.reposCount}")

            val bio = user.bio
            if (bio != null) {
                Text(
                    text = bio,
                    style = MaterialTheme.typography.caption.copy(fontStyle = FontStyle.Italic)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewGithubUserProfile() = AppTheme {
    GithubUserProfile(
        user = GithubUserUiModel(
            username = "johndoe",
            profileUrl = "abc.xyz",
            type = "User",
            name = "John Doe",
            companyName = "ABC XYZ",
            location = "PQR YXZ",
            bio = "Software Engineer",
            reposCount = 10,
            followers = 5
        )
    )
}

@Preview
@Composable
fun PreviewGithubUserProfile_NoCompanyAndLocation() = AppTheme {
    GithubUserProfile(
        user = GithubUserUiModel(
            username = "johndoe",
            profileUrl = "abc.xyz",
            type = "User",
            name = "John Doe",
            companyName = null,
            location = null,
            bio = "Software Engineer",
            reposCount = 10,
            followers = 5
        )
    )
}