package com.box.layoutcompose_f2j

import androidx.compose.runtime.compositionLocalOf


data class User (val name:String)
val ActiveUser = compositionLocalOf<User> { error("No user found") }
