package com.boss.dummyapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.boss.dummyapp.models.Product
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_item.view.*

class ProductAdapter(private val products : List<Product>,private val context: Context): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>(){

    class ProductViewHolder(view : View) : RecyclerView.ViewHolder(view){

        fun bindProduct(product: Product){
            itemView.product_title.text = product.title
            itemView.product_price.text = product.price
            itemView.product_category.text = product.category
            itemView.product_brand.text = product.brand
            Glide.with(itemView).load(product.poster).into(itemView.product_poster)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.product_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindProduct(products.get(position))

        val currentItem = products[position]
        holder.itemView.setOnClickListener {

            val intent = Intent(context,DescActivity::class.java)
            val bundle = bundleOf("image" to currentItem.poster,
            "title" to currentItem.title,
            "price" to currentItem.price,
            "category" to currentItem.category,
            "rating" to currentItem.rating,
            "description" to currentItem.description)

            intent.putExtras(bundle)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = products.size
}