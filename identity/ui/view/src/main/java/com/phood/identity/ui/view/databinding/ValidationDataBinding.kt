package com.phood.identity.ui.view.databinding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("app:isFieldValid", "app:errorMessage")
fun setErrorText(textInputLayout: TextInputLayout, isValid: Boolean, errorMessage: String) {
    textInputLayout.error = if (isValid) null else errorMessage
}
