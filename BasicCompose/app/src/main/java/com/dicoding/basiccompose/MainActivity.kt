package com.dicoding.basiccompose

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.basiccompose.ui.theme.BasicComposeTheme

private val sampleName = listOf(
    "Andre",
    "Desta",
    "Cistin",
    "Wendy",
    "Raphael",
    "Diana",
    "Lipos",
    "Threesha",
    "Irvan",
    "Nando"
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicComposeTheme {
                HelloJetpackComposeApp()
            }
        }
    }
}

@Composable
fun HelloJetpackComposeApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        GreetingList(sampleName)
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HelloJetpackComposeAppPreview() {
    BasicComposeTheme {
        HelloJetpackComposeApp()
    }
}

@Composable
fun GreetingList(names: List<String>) {
    if(names.isNotEmpty()){
//        Column {
//            for (name in names){
//                Greeting(name)
//            }
//        }
        LazyColumn {
            items(names.size) { name ->
                Greeting(names[name])
            }
        }
    } else {
        Text("No people to greet :(")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(name: String) {
    var isExpanded by remember { mutableStateOf(false) }
    val animatedSizeDp by animateDpAsState(
        targetValue = if (isExpanded) 120.dp else 80.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    Card (
        colors = CardDefaults.cardColors(contentColor = MaterialTheme.colorScheme.primary),
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
//        Image(
//            painter = painterResource(R.drawable.jetpack_compose),
//            contentDescription = "Logo Jetpack Compose",
//            modifier = Modifier.size(animatedSizeDp)
//        )
            Spacer(modifier = Modifier.width(8.dp))
            Column (modifier = Modifier.weight(1f)) {
                Text(
                    text = "Hello $name!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
                Text(text = "Welcome to Dicoding!")
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ExpandLess else Icons.Outlined.ExpandMore,
                    contentDescription = if (isExpanded) "Show less" else "Show more"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    BasicComposeTheme {
        Greeting("Android")
    }
}