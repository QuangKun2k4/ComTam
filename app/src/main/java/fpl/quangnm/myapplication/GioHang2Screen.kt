package fpl.quangnm.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import fpl.quangnm.myapplication.ui.theme.MyApplicationTheme

class GioHang2Screen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                HomeGioHang2()
            }
        }
    }
}

@Composable
fun HomeGioHang2() {
    // Thêm màu sắc và quản lý trạng thái giỏ hàng
    val themvaogiohang by remember { mutableStateOf("") }
    val mauNau = Color(0xFF373232)
    val mauCam = Color(0xFFFE724C)
    val context = LocalContext.current

    Scaffold(
        bottomBar = { BottomNavigationBarGioHang2() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mauNau)
                .padding(16.dp)
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Giỏ hàng",
                fontSize = 25.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 70.dp)
            )
            NoteMenuMon(PaddingValues(0.dp))

            Divider(color = Color.Gray, thickness = 1.dp)
            Spacer(modifier = Modifier.weight(1f))


            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Button(
                    onClick = {
                        val intent = Intent(context,ThanhToanScreen::class.java)
                        ContextCompat.startActivity(context,intent,null)},
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(width = 130.dp, height = 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Mua hàng",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Button(
                    onClick = {
                        val intent = Intent(context,HomeScreen::class.java)
                        ContextCompat.startActivity(context,intent,null)
                    },
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(width = 130.dp, height = 60.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Hủy",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

        }
    }
}

@Composable
fun BottomNavigationBarGioHang2() {
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
        // cột cho trang chủ
        BottomNavItem(
            icon = Icons.Filled.Home,
            label = "Trang chủ",
            onClick = {
                val intent = Intent(context, HomeScreen::class.java)
                ContextCompat.startActivity(context, intent, null)
            }
        )
        // cột cho lịch sử
        BottomNavItem(
            icon = Icons.Filled.History,
            label = "Lịch sử",
            onClick = {
                val intent = Intent(context, HistoriScreen::class.java)
                ContextCompat.startActivity(context, intent, null)
            }
        )
        // cột cho giỏ hàng
        BottomNavItem(
            icon = Icons.Filled.ShoppingCart,
            label = "Giỏ hàng",
            onClick = {
                val intent = Intent(context, GioHangScreen::class.java)
                ContextCompat.startActivity(context, intent, null)
            },
            selected = true
        )
        // cột cho hồ sơ
        BottomNavItem(
            icon = Icons.Filled.Person,
            label = "Hồ sơ",
            onClick = {
                val intent = Intent(context, ProfileScreen::class.java)
                ContextCompat.startActivity(context, intent, null)
            }
        )
    }
}

@Composable
fun BottomNavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    onClick: () -> Unit,
    selected: Boolean = false
) {
    val color = if (selected) Color(0xFFFE724C) else Color.White
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, color = color)
    }
}

@Composable
fun NoteMenuMon(paddingValues: PaddingValues) {
    val notes = listOf(
        Triple("Sườn", "25k", "01"),
        Triple("Sườn bì", "30k", "02")
    )
    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
            .background(Color(0xFF373232)),
        contentPadding = PaddingValues(8.dp) // Thêm padding cho các mục trong danh sách
    ) {
        // Sử dụng hàm items của LazyColumn để lặp qua danh sách
        items(notes) { (noteText, additionalText, soLuong) ->
            Menumon(noteText = noteText, additionalText = additionalText, soLuong = soLuong)
        }
    }
}


@Composable
fun Menumon(noteText: String, additionalText: String, soLuong: String) {
    val mauCam = Color(0xFFFE724C)
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(color = Color.LightGray, shape = MaterialTheme.shapes.medium)
            .padding(16.dp) // Padding cho toàn bộ Card
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // Căn đều không gian giữa các phần tử
        ) {
            // Phần hình ảnh và văn bản
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.comtam1),
                    contentDescription = null,
                    modifier = Modifier
                        .size(60.dp)
                        .clickable {
                            val intent = Intent(context, ChiTietSanPhamScreen::class.java)
                            ContextCompat.startActivity(context, intent, null)
                        }
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = noteText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = additionalText,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                Text(
                    text = "Số lượng: $soLuong",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun HomeGioHang2Preview() {
    MyApplicationTheme {
        HomeGioHang2()
    }
}
