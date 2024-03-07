package com.example.bend.register_login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.bend.Constants
import com.example.bend.components.BoldTextComponent
import com.example.bend.components.ClickableLoginRegisterText
import com.example.bend.components.MyButtonComponent
import com.example.bend.components.MyTextFieldComponent
import com.example.bend.components.NormalTextComponent
import com.example.bend.events.ForgotPassUIEvent
import com.example.bend.events.LoginUIEvent
import com.example.bend.ui.theme.green
import com.example.bend.view_models.ForgotPasswordViewModel

@Composable
fun ResetPasswordScreen(
    navController: NavController,
    reset_password_view_model: ForgotPasswordViewModel = viewModel()
) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(28.dp)

        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                BoldTextComponent(value = "Find your password")
                Spacer(modifier = Modifier.height(100.dp))
                NormalTextComponent(value = "Please enter your email:")
                MyTextFieldComponent(
                    labelValue = "Email",
                    onTextSelected = {
                        reset_password_view_model.onEvent(ForgotPassUIEvent.EmailChanged(it))
                    },
                    errorStatus = reset_password_view_model.forgot_pass_ui_state.value.email_error
                )
                Spacer(modifier = Modifier.height(25.dp))

                MyButtonComponent(
                    value = "Reset Password",
                    onButtonClicked = {
                        reset_password_view_model.onEvent(ForgotPassUIEvent.ResetButtonClicked(navController))
                    },
                    is_enabled = reset_password_view_model.email_validation_passed.value
                )
                Spacer(modifier = Modifier.height(8.dp))

            }
        }
        if(reset_password_view_model.forgot_pass_in_progress.value)
            CircularProgressIndicator()
    }
}