package fpl.quangnm.myapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.textFieldColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

class EditProfileScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                EditProfile()
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfile() {
    val context = LocalContext.current
    val mauNau = Color(0xFF373232)
    val mauCam = Color(0xFFFE724C)
    val mauXamTrang = Color(0xFFD9D9D9)
    val ok by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
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
            Text(text = "Quay lại", color = Color.White, modifier = Modifier.clickable {
                val intent = Intent(context, EditProfileScreen::class.java)
                ContextCompat.startActivity(context, intent, null)
            })
            Text(
                text = "Sửa hồ sơ",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

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
                var name by remember { mutableStateOf("Nguyễn Minh Quang") }
                var street by remember { mutableStateOf("TDP Số 3, ngách 230/21") }
                var houseNumber by remember { mutableStateOf("31A") }

                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = "Họ và tên",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    TextField(
                        value = name,
                        onValueChange = { name = it },
                        colors = textFieldColors(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        placeholder = { Text("Nhập họ và tên", color = Color.Gray) }
                    )
                    Text(
                        text = "Email",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                    )
                    TextField(
                        value = street,
                        onValueChange = { street = it },
                        colors = textFieldColors(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        placeholder = { Text("Nhập địa chỉ", color = Color.Gray) }
                    )

                    Text(
                        text = "Username",
                        color = Color.White,
                        modifier = Modifier.padding(bottom = 10.dp, top = 20.dp)
                    )
                    TextField(
                        value = houseNumber,
                        onValueChange = { houseNumber = it },
                        colors = textFieldColors(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(2.dp, Color.Gray, RoundedCornerShape(10.dp)),
                        placeholder = { Text("Nhập số nhà", color = Color.Gray) }
                    )

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Button(
                            onClick = {
                                // Handle button click
                            },
                            modifier = Modifier
                                .padding(10.dp, 30.dp, 10.dp)
                                .size(width = 160.dp, height = 55.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = mauCam,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Lưu", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }


            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun EditProfilePreview() {
    MyApplicationTheme {
        EditProfile()
    }
}