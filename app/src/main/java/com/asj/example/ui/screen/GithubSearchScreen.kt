package com.asj.example.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asj.example.core.GithubRepository
import com.asj.example.ui.components.GithubUserProfile
import com.asj.example.ui.components.GithubUserUiModel
import com.asj.example.ui.components.UsernameSearch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class GithubSearchUiState(
    val isLoading: Boolean,
    val user: GithubUserUiModel?,
    val errorMessage: String?
)

@HiltViewModel
class GithubSearchViewModel @Inject constructor(
    private val repository: GithubRepository
) : ViewModel() {
    private val _state = MutableStateFlow(
        GithubSearchUiState(
            isLoading = false,
            user = null,
            errorMessage = null
        )
    )
    val state = _state.asStateFlow()

    fun searchUser(username: String) {

        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }

            val fetchedUser = repository.findUser(username)

            if (fetchedUser == null) {
                _state.update { it.copy(errorMessage = "Invalid username", user = null) }
            } else {
                val user = GithubUserUiModel(
                    username = fetchedUser.username,
                    profileUrl = fetchedUser.profileUrl,
                    type = fetchedUser.type,
                    name = fetchedUser.name,
                    companyName = fetchedUser.companyName,
                    location = fetchedUser.location,
                    bio = fetchedUser.bio,
                    reposCount = fetchedUser.reposCount,
                    followers = fetchedUser.followers
                )
                _state.update { it.copy(user = user) }
            }
            _state.update { it.copy(isLoading = false) }
        }
    }
}


@Composable
fun GithubSearchScreen(viewModel: GithubSearchViewModel) {
    val state by viewModel.state.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        UsernameSearch(onSearch = { username -> viewModel.searchUser(username) })

        state.user?.let { GithubUserProfile(it) }

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.errorMessage?.let { Text(it, color = Color.Red) }
    }
}