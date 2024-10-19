package fpl.quangnm.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import fpl.quangnm.myapplication.api.Product
import fpl.quangnm.myapplication.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                Home()
        }
    }
}

@Composable
fun Home() {
    var textFieldValue by remember { mutableStateOf("") }
    val mauNau = Color(0xFF373232)
    val mauCam = Color(0xFFFE724C)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mauNau),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Logo
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo1),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .border(2.dp, mauCam, CircleShape)
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = "Welcome to MyApp",
                style = MaterialTheme.typography.titleLarge,
                color = Color.White,
                fontSize = 16.sp
            )
        }

        // Background Image
        Box(
            modifier = Modifier
                .width(280.dp)
                .height(120.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.comtam3),
                contentDescription = "Background Image",
                modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(10)).border(1.dp, Color.Gray, RoundedCornerShape(10)),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent.copy(alpha = 0.6f))
                    .border(1.dp, Color.Gray, RoundedCornerShape(10))
            )

            Text(
                text = "Welcome to MyApp",
                style = MaterialTheme.typography.headlineSmall.copy(color = Color.White),
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
            )
        }

        Luot()

        // Search TextField
        TextField(
            value = textFieldValue,
            onValueChange = { newValue -> textFieldValue = newValue },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(5.dp, 10.dp, 5.dp, 0.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp)),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White
            )
        )

        Scaffold(
            bottomBar = { BottomNavigationBar() },
        ) { innerPadding ->
            NoteApp(innerPadding)
        }
    }
}

@Composable
fun NoteApp(paddingValues: PaddingValues) {
    var notes by remember { mutableStateOf<List<Product>>(emptyList()) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val job = CoroutineScope(Dispatchers.IO).launch {
            val response = RetrofitClient.apiService.getProducts().execute()
            if (response.isSuccessful) {
                notes = response.body() ?: emptyList()
            } else {
                withContext(Dispatchers.Main) {
                    Toast.makeText(context, "Không thể tải dữ liệu: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            }
        }
        job.join()
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .background(Color(0xFF373232))
    ) {
        items(notes) { foodItem ->
            NoteCard(noteText = foodItem.name, additionalText = foodItem.price.toString(), imageUrl = foodItem.imageUrl)
        }
    }
}

@Composable
fun NoteCard(noteText: String, additionalText: String, imageUrl: String?) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium)
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Sử dụng hình ảnh mặc định nếu imageUrl là null
                val painter = rememberAsyncImagePainter(
                    model = imageUrl  // Thay default_image bằng hình ảnh mặc định của bạn
                )
                AsyncImage(model = imageUrl, contentDescription = null, modifier = Modifier.border(
                    BorderStroke(1.dp, Color.Red)
                ).size(50.dp).clickable {
                    val intent = Intent(context, ChiTietSanPhamScreen::class.java)
                        ContextCompat.startActivity(context, intent, null)
                })
//                Image(
//                    painter = painter,
//                    contentDescription = null,
//                    modifier = Modifier.size(60.dp).clickable {
//                        val intent = Intent(context, ChiTietSanPhamScreen::class.java)
//                        ContextCompat.startActivity(context, intent, null)
//                    }
//                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = noteText, style = MaterialTheme.typography.bodyLarge)
                    Text(text = additionalText, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
    }
}


@Composable
fun Luot() {
    val items = listOf(
        Triple("Cơm tấm", R.drawable.comtam1, "Cơm tấm description"),
        Triple("Topping", R.drawable.comtam2, "Topping description"),
        Triple("Đồ ăn thêm", R.drawable.comtam4, "Đồ ăn thêm description"),
        Triple("Khác", R.drawable.comtam5, "Khác description")
    )

    LazyRow(
        modifier = Modifier
            .padding(8.dp)
            .background(Color(0xFF373232)),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(items) { (title, imageRes, description) ->
            ItemCard(title = title, imageRes = imageRes)
        }
    }
}

@Composable
fun ItemCard(title: String, imageRes: Int) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = title,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 10.dp),
            color = Color.White
        )
        Spacer(modifier = Modifier.width(5.dp))
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(10))
                .border(5.dp, Color.Gray, RoundedCornerShape(10)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun BottomNavigationBar() {
    val mauCam = Color(0xFFFE724C)
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(Color(0xFF312C2C)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, HomeScreen::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Trang chủ",
                tint = mauCam,
                modifier = Modifier.size(30.dp)
            )
            Text("Trang chủ", color = mauCam)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, HistoriScreen::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.History,
                contentDescription = "Lịch sử",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text("Lịch sử", color = Color.White)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProfileScreen::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Hồ sơ",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text("Hồ sơ", color = Color.White)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, GioHangScreen::class.java)
                    ContextCompat.startActivity(context, intent, null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Giỏ hàng",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
            Text("Giỏ hàng", color = Color.White)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        Home()
}
