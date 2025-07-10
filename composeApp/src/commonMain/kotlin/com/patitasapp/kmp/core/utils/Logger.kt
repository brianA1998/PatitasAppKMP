package com.patitasapp.kmp.core.utils

/**
 * Utilidad simple para centralizar los logs de la aplicación
 */
object Logger {
    private const val TAG_AUTH = "PatitasAuth"

    /**
     * Log para la capa de presentación del flujo de autenticación
     */
    fun authPresentation(message: String) {
        println("$TAG_AUTH [Presentation] - $message")
    }

    /**
     * Log para la capa de dominio del flujo de autenticación
     */
    fun authDomain(message: String) {
        println("$TAG_AUTH [Domain] - $message")
    }

    /**
     * Log para la capa de datos del flujo de autenticación
     */
    fun authData(message: String) {
        println("$TAG_AUTH [Data] - $message")
    }

    /**
     * Log para la capa de datos externos (Firebase) del flujo de autenticación
     */
    fun authFirebase(message: String) {
        println("$TAG_AUTH [Firebase] - $message")
    }
}
