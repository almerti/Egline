package almerti.egline.feature.login

import almerti.egline.R
import almerti.egline.ui.components.CustomTextField
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import almerti.egline.ui.components.PasswordField
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.TextButton
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun LoginScreen() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        color = Color.Transparent,
        contentColor = Color.Black,
    ) {
        Row {
            Button(
                modifier = Modifier.size(36.dp),
                onClick = { /*TODO*/},
                content = {
                    Image(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = null,
                    )
                },
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.Gray,
                ),
                contentPadding = PaddingValues(2.dp),
            )
        }
        Column(
            modifier = Modifier.padding(top = 60.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .height(220.dp)
                    .width(200.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_launcher_foreground),
                contentDescription = null,
            )
            Text(
                modifier = Modifier.padding(bottom = 36.dp),
                text = stringResource(id = R.string.login_header),
                style = TextStyle(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp,
                    fontStyle = FontStyle.Normal,
                ),
            )
            CustomTextField(
                // email field
                placeholderText = stringResource(id = R.string.email_field),
                icon = R.drawable.baseline_email_24,
            )
            PasswordField()
            Button(
                onClick = { /*TODO*/},
                content = {
                    Text(text = stringResource(id = R.string.login_header))
                },
            )
            Spacer(modifier = Modifier.weight(1f))
            Row {
                Text(
                    text = stringResource(id = R.string.register_message_part1),
                )
                TextButton(
                    modifier = Modifier.height(22.dp),
                    onClick = { /* TODO{*} */},
                    contentPadding = PaddingValues(
                        start = 5.dp,
                        top = 1.dp,
                        end = 5.dp,
                        bottom = 0.dp,
                    ),
                    shape = RectangleShape,
                    content = {
                        Text(
                            text = stringResource(id = R.string.register_message_part2),
                            style = TextStyle(
                                color = Color.Blue,
                                fontSize = 16.sp,
                            ),
                        )
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
