package dev.techpolis.studservice.utils

import android.text.TextUtils
import android.util.Patterns


fun isValidEmail(target: String): Boolean {
    return if (TextUtils.isEmpty(target)) {
        false
    } else {
        Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}

fun isValidPassword(password: String): Boolean {
    return password.length in 8..20
}


fun isValidServiceTitle(title: String): Boolean {
    return title.length in 8..50
}