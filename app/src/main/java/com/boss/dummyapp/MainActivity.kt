package com.boss.dummyapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.boss.dummyapp.models.Product
import com.boss.dummyapp.models.ProductResponse
import com.boss.dummyapp.services.ProductApiInterface
import com.boss.dummyapp.services.ProductApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (checkForInternet(this)) {
            Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show()
            rv_movies_list.layoutManager = LinearLayoutManager(this)
            rv_movies_list.setHasFixedSize(true)
            getProductData { products: List<Product> ->
                rv_movies_list.adapter = ProductAdapter(products, this@MainActivity)
            }
        } else {
            Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show()
        }
    }
}

private fun getProductData(callback: (List<Product>) -> Unit) {
    val apiService = ProductApiService.getInstance().create(ProductApiInterface::class.java)
    apiService.getProductList().enqueue(object : Callback<ProductResponse> {
        override fun onResponse(
            call: Call<ProductResponse>,
            response: Response<ProductResponse>,
        ) {
            return callback(response.body()!!.products)
        }

        override fun onFailure(call: Call<ProductResponse>, t: Throwable) {

        }
    })
}

private fun checkForInternet(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        val network = connectivityManager.activeNetwork ?: return false

        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true


            else -> false
        }
    } else {

        @Suppress("DEPRECATION") val networkInfo =
            connectivityManager.activeNetworkInfo ?: return false
        @Suppress("DEPRECATION")
        return networkInfo.isConnected
    }
}