package com.example.secondexercise.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.secondexercise.R
import com.example.secondexercise.models.Restaurant

class RestaurantsAdapter(val context: Context, private val onClickItem: ClickItem) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    interface ClickItem{
        fun clickItem(restaurant: Restaurant)
    }

    inner class RestaurantViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var restaurantName: TextView = itemView.findViewById(R.id.tv_restaurant)
        var restaurantAddress :TextView = itemView.findViewById(R.id.tv_restaurant2)
        var restaurantImg :ImageView = itemView.findViewById(R.id.im_restaurant)
        var txtItems: TextView = itemView.findViewById(R.id.txtItems)
        var txtPrice: TextView = itemView.findViewById(R.id.txtPrice)

    }

    val differCallBack = object : DiffUtil.ItemCallback<Restaurant>(){
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.Id == newItem.Id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    fun submitList(list:ArrayList<Restaurant>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false))
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = differ.currentList[position]
        var restaurantName = if (restaurant.Name.length > 21)
            restaurant.Name.subSequence(0,20).toString().plus("...")
                else restaurant.Name
        var randomNmber = (Math.random() * 100).toString()
        var items =randomNmber.subSequence(randomNmber.indexOf('.')+10,randomNmber.indexOf('.')+12 ).toString().plus(" items")
        var txtPrice = randomNmber.subSequence(0, randomNmber.indexOf('.')+2).toString().plus("$")

        holder.restaurantName.text = restaurantName;
        holder.restaurantAddress.text = restaurant.City
        holder.txtItems.text = items
        holder.txtPrice.text = txtPrice
        Glide.with(context).load(restaurant.PicturePath).placeholder(R.drawable.ic_facebook).centerCrop().
        into(holder.restaurantImg)
        holder.restaurantImg.setOnClickListener {
            onClickItem.clickItem(restaurant)
        }
        holder.restaurantName.setOnClickListener {
            onClickItem.clickItem(restaurant)
        }
        holder.restaurantAddress.setOnClickListener {
            onClickItem.clickItem(restaurant)
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}
