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
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import fpl.quangnm.myapplication.api.RetrofitClient
import fpl.quangnm.myapplication.api.User
import retrofit2.Call
import retrofit2.Response

class RegisterScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterPreview()
        }
    }
}

@Composable
fun RegisterPreview() {
    val mauNau = Color(0xFF373232)
    val context = LocalContext.current
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var cfpassword by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(mauNau)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "Logo",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(9.dp, Color.Gray, CircleShape)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Register account", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)

        // Input fields
        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Họ tên") },
            modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
            )
        )

        OutlinedTextField(
            value = cfpassword,
            onValueChange = { cfpassword = it },
            label = { Text("Confirm Password") },
            modifier = Modifier.fillMaxWidth().padding(5.dp, 10.dp),
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

        // Error message display
        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                errorMessage = ""

                when {
                    fullName.isBlank() -> errorMessage = "Vui lòng nhập họ tên."
                    email.isBlank() -> errorMessage = "Vui lòng nhập email."
                    username.isBlank() -> errorMessage = "Vui lòng nhập tên đăng nhập."
                    password.isBlank() -> errorMessage = "Vui lòng nhập mật khẩu."
                    password != cfpassword -> errorMessage = "Mật khẩu không khớp."
                    else -> {
                        isLoading = true
                        val user = User(name = fullName, email = email, username = username, password = password)

                        RetrofitClient.apiService.registerUser(user).enqueue(object : retrofit2.Callback<User> {
                            override fun onResponse(call: Call<User>, response: Response<User>) {
                                isLoading = false
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Đăng ký thành công", Toast.LENGTH_LONG).show()
                                    // Chuyển hướng đến LoginScreen
                                    val intent = Intent(context, MainActivity::class.java).apply {
                                        putExtra("EMAIL", email) // truyền email
                                        putExtra("PASSWORD", password) // truyền password
                                    }
                                    ContextCompat.startActivity(context, intent, null)
                                } else {
                                    errorMessage = "Đăng ký thất bại. Vui lòng thử lại."
                                }
                            }

                            override fun onFailure(call: Call<User>, t: Throwable) {
                                isLoading = false
                                errorMessage = "Lỗi: ${t.message}"
                            }
                        })
                    }
                }
            },
            modifier = Modifier.size(width = 210.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Text(text = if (isLoading) "Đang đăng ký..." else "Đăng ký", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

        Button(
            onClick = {
                val intent = Intent(context, MainActivity::class.java)
                ContextCompat.startActivity(context, intent, null)
            },
            modifier = Modifier.padding(5.dp).size(width = 210.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
        ) {
            Text(text = "Back", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreviewPreview() {
    RegisterPreview()
}
