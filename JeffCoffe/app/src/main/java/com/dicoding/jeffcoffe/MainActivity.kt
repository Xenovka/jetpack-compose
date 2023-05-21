package com.dicoding.jeffcoffe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dicoding.jeffcoffe.model.dummyCategory
import com.dicoding.jeffcoffe.ui.components.CategoryItem
import com.dicoding.jeffcoffe.ui.components.SearchBar
import com.dicoding.jeffcoffe.ui.components.SectionText
import com.dicoding.jeffcoffe.ui.theme.JeffCoffeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JeffCoffeTheme {
                JeffCoffeeApp()
            }
        }
    }
}

@Composable
fun JeffCoffeeApp(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Banner()
        SectionText(stringResource(R.string.section_category))
        CategoryRow()
    }
}

@Composable
fun Banner(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(160.dp)
        )
        SearchBar()
    }
}

@Composable
fun CategoryRow(
    modifier: Modifier = Modifier,
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier.padding(16.dp)
    ) {
        items(dummyCategory, key = { it.textCategory }) { category ->
            CategoryItem(category)
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun CategoryRowPreview() {
//    JeffCoffeTheme {
//        CategoryRow()
//    }
//}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun JeffCoffeeAppPreview() {
    JeffCoffeTheme {
        JeffCoffeeApp()
    }
}