package com.anotn.flea.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.anotn.flea.designsystem.component.CheckboxComponent
import com.anotn.flea.designsystem.component.CustomTextFieldComponent
import com.anotn.flea.designsystem.component.HeadingTextComponent
import com.anotn.flea.designsystem.component.NormalTextComponent
import com.anotn.flea.designsystem.component.PasswordFieldComponent
import com.anotn.flea.designsystem.theme.White

@Composable
fun SignUpScreen(
) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(White)
            .padding(28.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NormalTextComponent(value = "Hello")
            HeadingTextComponent(value = "Sign Up")
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextFieldComponent(labelValue = "First Name", Icons.Outlined.AccountCircle)
            CustomTextFieldComponent(labelValue = "Last Name", Icons.Outlined.AccountCircle)
            CustomTextFieldComponent(labelValue = "Email", Icons.Outlined.Email)
            PasswordFieldComponent(labelValue = "Password", Icons.Outlined.Lock)
            CheckboxComponent(value = "By signing up, you agree to our Terms of Use and Privacy Policy.")
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}