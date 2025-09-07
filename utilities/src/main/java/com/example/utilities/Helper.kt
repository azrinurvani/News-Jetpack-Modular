package com.example.utilities

import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLHandshakeException
import javax.net.ssl.SSLPeerUnverifiedException

fun Throwable.toErrorMessage(): String {
    return when (this) {
        is SocketTimeoutException -> "Connection Timed Out. Please try again."
        is InterruptedIOException -> "Request timed out. Please try again."
        is UnknownHostException -> "No Internet Connection"
        is ConnectException -> "Unable to connect to server."
        is SSLHandshakeException,
        is SSLPeerUnverifiedException -> "Secure connection failed."
        is HttpException -> when (code()) {
            400 -> "Invalid request. Please try again."
            401 -> "Session expired. Please log in again."
            403 -> "You don't have permission to perform this action."
            404 -> "Requested resource not found."
            in 500..599 -> "Server is currently unavailable. Please try later."
            else -> "Unexpected server error."
        }
        is IOException -> "Network error. Please check your connection."
        else -> "Unexpected error occurred."
    }
}
/**
 * Note for IOException hierarchy
 * Throwable
 *  └─ Exception
 *      └─ IOException
 *          └─ InterruptedIOException
 *              └─ SocketTimeoutException
 * */