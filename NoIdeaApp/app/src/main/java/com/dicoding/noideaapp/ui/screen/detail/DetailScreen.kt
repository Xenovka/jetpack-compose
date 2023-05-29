package com.dicoding.noideaapp.ui.screen.detail

import android.text.style.ClickableSpan
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.dicoding.noideaapp.R
import com.dicoding.noideaapp.di.Injection
import com.dicoding.noideaapp.ui.ViewModelFactory
import com.dicoding.noideaapp.ui.common.UiState
import com.dicoding.noideaapp.ui.theme.NoIdeaAppTheme

@Composable
fun DetailScreen(
    rangerId: String,
    viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ),
    navigateBack: () -> Unit,
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState) {
            is UiState.Loading -> {
                viewModel.getRangerById(rangerId)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailContent(
                    photoUrl = data.photoUrl,
                    title = data.name,
                    description = data.description,
                    squadName = data.squadName,
                    color = data.color,
                    role = data.color   ,
                    onBackClick = navigateBack
                )
            }
            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    photoUrl: String,
    title: String,
    description: String,
    squadName: String,
    color: String,
    role: String,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                    modifier = Modifier
                        .padding(16.dp)
                        .clickable { onBackClick() }
                )
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Medium,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }

            AsyncImage(
                model = photoUrl,
                contentDescription = title,
                modifier = modifier
                    .fillMaxWidth()
                    .height(300.dp)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.rangerRole, role),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = stringResource(R.string.rangerColor, color),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = stringResource(R.string.rangerSquad, squadName),
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.labelMedium
                )
                Text(
                    text = description,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = modifier.padding(top = 22.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailContentPreview() {
    NoIdeaAppTheme {
        DetailContent(
            "https://static.wikia.nocookie.net/powerrangers/images/a/a2/Red_Dino_Ranger_%26_AbaRed.png/revision/latest/scale-to-width-down/1000?cb=20230528152057",
            "Red Dino Ranger",
            "Conner McKnight is the Red Dino Ranger and leader of the Dino Rangers.\n\nRetroactively, he is also referred to as the Dino Thunder Red Ranger or Red Dino Thunder Ranger though these are in reference to the show, as opposed to proper labels. Conner has an identical twin brother named Eric McKnight (also played by James Napier) who was once a student at the Wind Ninja Academy, but later flunked out.",
            "Dino Thunder",
            "Red",
            "Conner McKnight",
            {},
        )
    }
}