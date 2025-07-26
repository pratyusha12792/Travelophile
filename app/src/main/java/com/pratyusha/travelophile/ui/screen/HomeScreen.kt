package com.pratyusha.travelophile.ui.screen

import android.R
import android.R.attr.shape
import android.icu.number.NumberFormatter.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExpandedDockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabIndicatorScope
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopSearchBar
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pratyusha.travelophile.data.Place
import com.pratyusha.travelophile.data.listOfPlaces
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun HomeScreen(){
    val listOfCategory  = listOf(
        "All",
        "Beaches",
        "Mountains",
        "Temples",
        "Cities"
    )
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    val scope = rememberCoroutineScope()
    val searchBarState = rememberSearchBarState()
    val textFieldState = rememberTextFieldState()
    val screenWidthDp = LocalConfiguration.current.screenWidthDp.dp
    val inputField = 
        @Composable{
            SearchBarDefaults.InputField(
                modifier = Modifier.width(screenWidthDp * 0.75f),
                searchBarState = searchBarState,
                textFieldState = textFieldState,
                onSearch = {

                },
                placeholder = {Text(text = "Search...")},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
            )
        }
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        LazyColumn (modifier = Modifier.fillMaxSize()){
            item {
                Text(
                    text = "Dream Explore Discover <3",
                    fontFamily = FontFamily.SansSerif,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF065D5D),
                    modifier = Modifier.padding(start = 27.dp, top = 16.dp)
                )
            }
            item {
                Spacer(modifier = Modifier.padding(top = 10.dp))
            }
            item {
                Row(
                    modifier = Modifier.padding(start = 12.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TopSearchBar(
                        state = searchBarState,
                        inputField = inputField,
                        windowInsets = WindowInsets(0),
                        modifier = Modifier.width(screenWidthDp * 0.8f)
                    )
                    ExpandedDockedSearchBar(
                        state = searchBarState,
                        inputField = inputField
                    ) { }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = {},
                        shape = CircleShape,
                        modifier = Modifier
                            .size(48.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF065D5D),
                            contentColor = Color.White
                        ),
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                }
            }
            item {
                SecondaryScrollableTabRow(
                    selectedTabIndex = selectedIndex,
                    edgePadding = 0.dp,
                    divider = {},
                    indicator = {
                        TabRowDefaults.PrimaryIndicator(
                            modifier = Modifier.tabIndicatorOffset(selectedIndex),
                            width = 40.dp
                        )
                    }
                ) {
                    listOfCategory.forEachIndexed { index, item ->
                        Tab(
                            selected = selectedIndex == index,
                            onClick = {
                                selectedIndex = index
                            },
                            text = {
                                Text(
                                    text = item,
                                    color = if (selectedIndex == index) {
                                        Color(0xFF065D5D)
                                    } else {
                                        Color.Gray
                                    },
                                    fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium,
                                )
                            }
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
                LazyRow (
                    modifier = Modifier.padding(start = 0.dp),
                    contentPadding = PaddingValues(horizontal = 0.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ){
                    item{
                        Spacer(modifier = Modifier.width(0.dp))
                    }
                    itemsIndexed(listOfPlaces){index, item ->
                        Card (
                            modifier = Modifier
                                .height(260.dp)
                                .width(200.dp),
                            shape = RoundedCornerShape(32.dp),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                        ){
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color.Transparent)
                            ){
                                Image(
                                    painter = painterResource(item.placeImage),
                                    contentDescription = "Place Image",
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(brush = Brush.verticalGradient(
                                            colors = listOf(
                                                Color.Transparent,
                                                Color(0xCC000000)
                                            ),
                                            startY = 100f,
                                            endY = Float.POSITIVE_INFINITY
                                        )),
                                )
                                Column (
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.Transparent)
                                        .padding(horizontal = 16.dp, vertical = 16.dp),
                                    horizontalAlignment = Alignment.Start,
                                    verticalArrangement = Arrangement.Bottom
                                ){
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = item.placeName,
                                        style = MaterialTheme.typography.bodyLargeEmphasized,
                                        fontSize = 20.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = item.location,
                                        style = MaterialTheme.typography.titleSmall,
                                        color = Color(0xFFD5D1D1)
                                    )
                                }
                            }
                        }
                    }
                    item{
                        Spacer(modifier = Modifier.width(0.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(25.dp))
            }
            item {
                Text(
                    text = "Popular Destinations",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF065D5D),
                    fontSize = 20.sp,
                    modifier = Modifier.padding(start = 27.dp)
                )
            }
            item{
                Spacer(modifier = Modifier.height(16.dp))
            }
            itemsIndexed(listOfPlaces) { index, item ->
                Column {
                    Card(
                        modifier = Modifier
                            .height(140.dp)
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        shape = RoundedCornerShape(32.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        onClick = {}
                    ) {
                        Box (
                            contentAlignment = Alignment.Center
                        ){
                            Image(
                                painter = painterResource(item.placeImage),
                                contentDescription = "Place Image",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Box(
                                modifier = Modifier.fillMaxSize()
                                    .background(color = Color.Black.copy(alpha = 0.55f))
                            )
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = item.placeName,
                                    style = MaterialTheme.typography.bodyLargeEmphasized,
                                    fontSize = 23.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White

                                )
                                Text(
                                    text = item.location,
                                    style = MaterialTheme.typography.titleSmall,
                                    color = Color(0xFFD5D1D1)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}