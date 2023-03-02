package com.boss.dummyapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DescActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_desc)
        val productTitle: TextView = findViewById(R.id.item_title)
        val productCategory: TextView = findViewById(R.id.item_category)
        val productImage: ImageView = findViewById(R.id.item_img)
        val productRating: TextView = findViewById(R.id.item_rating)
        val productPrice: TextView = findViewById(R.id.item_price)
        val productDesc: TextView = findViewById(R.id.item_description)

        val image = intent.getStringExtra("image")
        val title = intent.getStringExtra("title")
        val price = intent.getStringExtra("price")
        val category = intent.getStringExtra("category")
        val rating = intent.getStringExtra("rating")
        val description = intent.getStringExtra("description")

        Glide.with(this).load(image).centerCrop().into(productImage)

        productTitle.text = title
        productCategory.text = category
        productPrice.text = price
        productRating.text = rating
        productDesc.text = description

    }
}