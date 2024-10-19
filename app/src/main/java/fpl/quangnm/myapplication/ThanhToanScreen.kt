@file:Suppress("DEPRECATION")
@file:OptIn(ExperimentalMaterial3Api::class)

package fpl.quangnm.myapplication

import android.R
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.quangnm.myapplication.ui.theme.MyApplicationTheme

class ThanhToanScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentScreen()
        }
    }
}

@Composable
fun PaymentScreen() {
    val items = listOf(
        Pair("Cơm tấm", "25k"),
        Pair("Topping", "10k"),
        Pair("Đồ ăn thêm", "15k")
    )

    // Tính tổng
    val totalAmount = items.sumBy { it.second.removeSuffix("k").toInt() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C2C2C)),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Thanh toán",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        LazyColumn(
            modifier = Modifier.weight(1f).padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { (itemName, itemPrice) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = itemName, color = Color.White)
                    Text(text = itemPrice, color = Color.White)
                }
            }
        }

        Divider(color = Color.Gray, thickness = 1.dp)

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Tổng cộng", color = Color.White, style = MaterialTheme.typography.bodySmall)
            Text(text = "$totalAmount k", color = Color.White, style = MaterialTheme.typography.bodySmall)
        }

        // Phần nhập thông tin thanh toán
        PaymentForm()

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { /* Xử lý thanh toán */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(RoundedCornerShape(10.dp))
        ) {
            Text(text = "Xác nhận thanh toán", fontSize = 18.sp)
        }
    }
}

@Composable
fun PaymentForm() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        CustomTextInput(
            label = "Tên chủ thẻ",
            placeholder = "Nguyễn Văn A"
        )
        CustomTextInput(
            label = "Số thẻ",
            placeholder = "1234 5678 9876 5432"
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CustomTextInput(
                label = "Ngày hết hạn",
                placeholder = "MM/YY",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(16.dp))
            CustomTextInput(
                label = "CVV",
                placeholder = "123",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun CustomTextInput(label: String, placeholder: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = label, color = Color.White, style = MaterialTheme.typography.bodyMedium)
        TextField(
            value = "",
            onValueChange = {},
            placeholder = {
                Text(text = placeholder, color = Color.Gray)
            },
            modifier = Modifier
                .fillMaxWidth()
                .border(BorderStroke(1.dp, Color.Gray), shape = RoundedCornerShape(10.dp))
                .padding(8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White,
                focusedTextColor = Color.White
            )
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview5() {
    PaymentScreen()
}