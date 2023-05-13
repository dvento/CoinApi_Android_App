package com.danvento.coinapi.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.danvento.coinapi.domain.model.AssetItem
import com.danvento.coinapi.ui.theme.CoinApiTheme
import com.danvento.coinapi.ui.viewmodel.MainActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {

    private val viewModel: MainActivityViewModel by viewModel()

    // TODO: show dollar price
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {




            CoinApiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    

                    val assetList = listOf(AssetItem(
                        "bitcoin",
                        "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/4caf2b16a0174e26a3482cea69c34cba.png",
                        "BTC",
                        990.0
                    ))

                    AssetList(viewModel = viewModel)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinApiTheme {
    }
}

// TODO: if image is blank
// TODO: if EUR is 0.0 show dollar
// TODO: price is null "no info available"
// TODO: mark if it's crypto

@Preview
@Composable
fun AssetListItem(
    assetItem: AssetItem = AssetItem(
        "bitcoin",
        "https://s3.eu-central-1.amazonaws.com/bbxt-static-icons/type-id/png_64/4caf2b16a0174e26a3482cea69c34cba.png",
        "BTC",
        990.0
    ),
    backgroundColor: Color = Color.LightGray,
    onItemClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .clickable { onItemClick() }
            .padding(8.dp)
            .background(backgroundColor)
    ) {

        Row(
            modifier = modifier
                .padding(16.dp)
        ) {
            AsyncImage(
                model = assetItem.iconUrl,
                contentDescription = "icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .border(
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = CircleShape
                    )
                    .padding(3.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = assetItem.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = assetItem.priceEur.toString(),
                    fontWeight = FontWeight.Light,
                    fontSize = 16.sp,
                )
            }
        }
    }
}


@Composable
fun AssetList(viewModel: MainActivityViewModel) {


    viewModel.getAssets()

    val assetsData by viewModel.assetData.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        LazyColumn {
            items(assetsData) { asset ->
                AssetListItem(
                    asset,
                    backgroundColor = Color.White,
                    onItemClick = {
                        Log.i("ActorList", "Info $asset")
                    },
                )
            }
        }
    }
}
