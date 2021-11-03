package com.box.layoutcompose_f2j

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.box.layoutcompose_f2j.ui.theme.LayoutCompose_f2jTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutCompose_f2jTheme {
//                LayoutsCodelab()
                ImageList()
                // A surface container using the 'background' color from the theme
//                Surface(color = MaterialTheme.colors.background) {
//                    PhotographerCard()
//                }
            }
        }
    }
}


@Composable
fun LayoutsCodelab() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()


    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text("Layout") }, actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.AccountBalance, contentDescription = null)
                }
            }, navigationIcon = {
                IconButton(onClick = {
                    coroutineScope.launch {
                        scaffoldState.drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = null)

                }
            })
        },
        bottomBar = {
            BottomNavigation {
                Row {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.AccountBalance, contentDescription = null)
                    }
                    IconButton(onClick = { }) {
                        Icon(imageVector = Icons.Filled.AccountBalance, contentDescription = null)
                    }
                }
            }
        },

        drawerContent = {
            Text(text = "one")
            Spacer(modifier = Modifier)
            Text(text = "two")
        },


        ) { innerPadding ->
        val user = User("yg")
        CompositionLocalProvider(ActiveUser provides user) {
            BodyContent(Modifier.padding(innerPadding))
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
        LocalTestCompose()
    }

}
@Composable
fun LocalTestCompose(){
    Text(text = ActiveUser.current.name)
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .padding(8.dp)
        .clip(RoundedCornerShape(5.dp))
        .background(MaterialTheme.colors.surface)
        .clickable { }
        .padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.3f)
        ) {

        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)

            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun ImageList() {
    val listSize = 100
    val scrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    Column(modifier = Modifier.fillMaxWidth()) {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    scrollState.animateScrollToItem(0)
                }
            }) {
               Text(text = "Go Top")
            }
            Button(onClick = {
               coroutineScope.launch {
                   scrollState.animateScrollToItem(listSize - 1)
               }
            }) {
               Text(text = "Go Bottom")
            }
        }
        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(index = it)
            }
        }
    }
}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ) {
//                Log.d("Image$index", this.build().toString())
            },
            contentDescription = "",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Preview(showBackground = true)
@Composable
fun ImageListPreview() {
    LayoutCompose_f2jTheme {
        ImageList()
    }
}

@Preview(showBackground = true)
@Composable
fun PhotographerCardPreview() {
    LayoutCompose_f2jTheme {
        PhotographerCard()
    }
}

@Preview(showBackground = true, name = "second")
@Composable
fun LayoutsCodelabPreview() {
    LayoutCompose_f2jTheme {
//        LayoutsCodelab()
    }
}