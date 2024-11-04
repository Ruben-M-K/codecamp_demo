package com.example.cc_demo.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkRepository(private val context: Context) {

    val networkStatus = MutableStateFlow(false)

    init {
        val networkRequest =
            NetworkRequest.Builder().addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR).build()

        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                networkStatus.tryEmit(true)
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                networkStatus.tryEmit(false)
            }
        }

        val connectivityManager =
            ContextCompat.getSystemService(
                context,
                ConnectivityManager::class.java
            ) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

}