package fpl.quangnm.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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

class GioHangScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                HomeGioHang()
            }
        }
    }
}

@Composable
fun HomeGioHang() {
    val mauNau = Color(0xFF373232)
    val mauCam = Color(0xFFFE724C)

    Scaffold(
        bottomBar = { BottomNavigationBarGioHang() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(mauNau)
                .padding(16.dp)
                .padding(innerPadding), // Thêm padding cho nội dung
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Giỏ hàng", fontSize = 25.sp, color = Color.White, fontWeight = FontWeight.Bold , modifier = Modifier.padding(bottom = 70.dp))
            Image(
                painter = painterResource(id = R.drawable.shopping_cart),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(240.dp)
                    .padding(end = 15.dp)
            )
            Text(text = "Không có đơn hàng nào", color = Color.Red, fontSize = 20.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 30.dp, bottom = 30.dp))
            Button(onClick = {},modifier = Modifier
                .padding(10.dp,30.dp,10.dp)
                .size(width = 200.dp, height = 60.dp)
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White, //  nền
                    contentColor = Color.Black //  chữ
                )) {
                    Text(text = "Thêm vào giỏ hàng", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.weight(1f)) // Spacer này có thể không cần thiết nếu bạn muốn logo nằm ở giữa
        }
    }
}


@Composable
fun BottomNavigationBarGioHang() {
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
                    ContextCompat.startActivity(context,intent,null)
                }
                .padding(vertical = 8.dp) // Điều chỉnh padding cho dễ nhìn
        ) {
            Icon(
                imageVector = Icons.Filled.Home,
                contentDescription = "Trang chủ",
                tint = Color.White,
                modifier = Modifier.size(24.dp) // Kích thước icon
            )
            Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách giữa icon và text
            Text("Trang chủ", color = Color.White)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, HistoriScreen::class.java)
                    ContextCompat.startActivity(context,intent,null) }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.History,
                contentDescription = "Lịch sử",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Lịch sử", color = Color.White)
        }

        // Giỏ hàng
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {  val intent = Intent(context, GioHangScreen::class.java)
                    ContextCompat.startActivity(context,intent,null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Giỏ hàng",
                tint = mauCam,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Giỏ hàng", color = Color(0xFFFE724C))
        }

        // Hồ sơ
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .clickable {
                    val intent = Intent(context, ProfileScreen::class.java)
                    ContextCompat.startActivity(context,intent,null)
                }
                .padding(vertical = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Person,
                contentDescription = "Hồ sơ",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text("Hồ sơ", color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GioHangPreview() {
    MyApplicationTheme {
        HomeGioHang()
    }
}