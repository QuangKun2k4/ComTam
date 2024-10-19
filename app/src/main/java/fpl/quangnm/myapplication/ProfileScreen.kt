package fpl.quangnm.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import fpl.quangnm.myapplication.ui.theme.MyApplicationTheme

class ProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Profile()
            }
        }
    }
}

@Composable
fun Profile() {
    val context = LocalContext.current
    val mauNau = Color(0xFF373232)
    val mauCam = Color(0xFFFE724C)
    val mauXamTrang = Color(0xFFD9D9D9)

    Scaffold(
        bottomBar = { BottomNavigationBarProfile() }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp)
                    .background(Color.Black)
                    .padding(bottom = 20.dp, start = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = "Edit", color = Color.White, modifier = Modifier.clickable {
                    val intent = Intent(context, EditProfileScreen::class.java)
                    ContextCompat.startActivity(context, intent, null)
                })
                Text(
                    text = "Hồ sơ",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(text = "Signout", color = Color.White, modifier = Modifier.clickable {
                    val intent1 = Intent(context, MainActivity::class.java)
                    ContextCompat.startActivity(context, intent1, null)
                })
            }
            Column(
                modifier = Modifier
                    .background(mauNau)
                    .fillMaxSize()
                    .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
                    .border(2.dp, Color.Gray, RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.man),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                        .padding(top = 30.dp)
                )
                Text(text = "Quang", color = Color.White, modifier = Modifier.padding(top = 15.dp))
                Column(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center,
                )
                {
                    Text(
                        text = "Số điện thoại",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .background(mauXamTrang).height(50.dp).clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "0971362896", modifier = Modifier.padding(start = 20.dp))
                    }

                    Text(
                        text = "Phường",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, top = 20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .background(mauXamTrang).height(50.dp).clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Mễ Trì Thượng", modifier = Modifier.padding(start = 20.dp))
                    }
                    Text(
                        text = "(Đuường / Tổ Dân Phố) / Ngách",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, top = 20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .background(mauXamTrang).height(50.dp).clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            text = "TDP Số 3, ngách 230/21",
                            modifier = Modifier.padding(start = 20.dp)
                        )
                    }
                    Text(
                        text = "Số nhà",
                        color = Color.White,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, top = 20.dp)
                    )
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp)
                            .background(mauXamTrang).height(50.dp).clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "31A", modifier = Modifier.padding(start = 20.dp))
                    }
                }
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
                        .clickable { /* TODO: Handle onClick */ }
                        .padding(vertical = 8.dp) // Điều chỉnh padding cho dễ nhìn
                ) {
                    Icon(
                        imageVector = Icons.Filled.Home,
                        contentDescription = "Trang chủ",
                        tint = mauCam,
                        modifier = Modifier.size(24.dp) // Kích thước icon
                    )
                    Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách giữa icon và text
                    Text("Trang chủ", color = Color(0xFFFE724C))
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { /* TODO: Handle onClick */ }
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
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text("Giỏ hàng", color = Color.White)
                }

                // Hồ sơ
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable { /* TODO: Handle onClick */ }
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
    }
}

    @Composable
    fun BottomNavigationBarProfile() {
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
                        ContextCompat.startActivity(context, intent, null) }
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
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("Giỏ hàng", color = Color.White)
            }

            // Hồ sơ
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable { /* TODO: Handle onClick */ }
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Hồ sơ",
                    tint = mauCam,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text("Hồ sơ", color = mauCam)
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        MyApplicationTheme {
            Profile()
        }
    }
