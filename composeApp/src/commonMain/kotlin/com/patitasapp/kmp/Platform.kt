package com.patitasapp.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform