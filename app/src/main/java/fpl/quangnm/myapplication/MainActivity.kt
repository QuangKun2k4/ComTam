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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import fpl.quangnm.myapplication.api.LoginRequest
import fpl.quangnm.myapplication.api.LoginResponse
import fpl.quangnm.myapplication.api.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LoginScreen()
        }
    }
}

@Composable
fun LoginScreen() {
    val mauNau = Color(0xFF373232)
    val context = LocalContext.current

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

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

        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Login", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 30.sp)

        Spacer(modifier = Modifier.height(40.dp))

        // Username TextField
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp),
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

        Spacer(modifier = Modifier.height(8.dp))

        // Password TextField
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 10.dp),
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None // Hiển thị văn bản khi isPasswordVisible là true
            } else {
                PasswordVisualTransformation() // Hiển thị dấu chấm khi isPasswordVisible là false
            },
            trailingIcon = {
                IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                    Icon(
                        imageVector = if (isPasswordVisible) {
                            Icons.Filled.Visibility // Hiển thị biểu tượng "hiện"
                        } else {
                            Icons.Filled.VisibilityOff // Hiển thị biểu tượng "ẩn"
                        },
                        contentDescription = if (isPasswordVisible) "Hide password" else "Show password"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.White,
                unfocusedIndicatorColor = Color.Gray,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White // Đặt màu văn bản không có tiêu điểm
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Hiển thị thông báo lỗi
        if (errorMessage.isNotBlank()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                errorMessage = ""
                when {
                    username.isBlank() -> errorMessage = "Vui lòng nhập tên đăng nhập."
                    password.isBlank() -> errorMessage = "Vui lòng nhập mật khẩu."
                    else -> {
                        isLoading = true
                        val loginRequest = LoginRequest(username = username, password = password)
                        RetrofitClient.apiService.loginUser(loginRequest).enqueue(object : retrofit2.Callback<LoginResponse> {
                            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                                isLoading = false
                                if (response.isSuccessful) {
                                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                                    context.startActivity(Intent(context, HomeScreen::class.java))
                                } else {
                                    errorMessage = "Đăng nhập thất bại. Vui lòng thử lại."
                                }
                            }

                            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                                isLoading = false
                                errorMessage = "Lỗi: ${t.message}"
                            }
                        })
                    }
                }
            },
            modifier = Modifier
                .padding(10.dp, 30.dp)
                .fillMaxWidth()
                .size(width = 210.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = if (isLoading) "Đang đăng nhập..." else "Đăng nhập", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                context.startActivity(Intent(context, RegisterScreen::class.java))
            },
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .size(width = 210.dp, height = 50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        ) {
            Text(text = "Đăng ký", fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun PreviewGreeting() {
    LoginScreen()
}
