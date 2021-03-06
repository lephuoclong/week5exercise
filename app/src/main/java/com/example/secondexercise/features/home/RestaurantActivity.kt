package com.example.secondexercise.features.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.*
import com.example.secondexercise.R
import com.example.secondexercise.adapters.RestaurantsAdapter
import com.example.secondexercise.databinding.ActivityRestaurantBinding
import com.example.secondexercise.models.Restaurant
import com.example.secondexercise.viewmodels.HomeViewModel


class RestaurantActivity : AppCompatActivity() {
    private lateinit var binding:ActivityRestaurantBinding
    private lateinit var viewModel:HomeViewModel
    private lateinit var adapterRestaurant:RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_restaurant)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_restaurant)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getListRestaurant(this@RestaurantActivity)
        viewModel.listRestaurant.observe(this, Observer {list->
            viewModel.menuOptions.observe(this, Observer { option ->
                setUpRecyclerView(list, option)
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_select_layout, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_listView ->viewModel.onChangeMenu(false)
            R.id.menu_gridView -> viewModel.onChangeMenu(true)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun setUpRecyclerView(list:ArrayList<Restaurant>, option:Boolean) {
        adapterRestaurant = RestaurantsAdapter(this@RestaurantActivity, object : RestaurantsAdapter.ClickItem{
            override fun clickItem(restaurant: Restaurant) {
               showAlertDialogDeleteRestaurant(restaurant)
            }
        })
        adapterRestaurant.submitList(list)
        binding.rcvRestaurant.layoutManager = if(!option) LinearLayoutManager(this) else GridLayoutManager(this, 2)
        binding.rcvRestaurant.setHasFixedSize(true)
        binding.rcvRestaurant.itemAnimator = DefaultItemAnimator()
        binding.rcvRestaurant.addItemDecoration(DividerItemDecoration( binding.rcvRestaurant.context, DividerItemDecoration.VERTICAL))
        binding.rcvRestaurant.adapter = adapterRestaurant
    }
    private fun showAlertDialogDeleteRestaurant(restaurant: Restaurant){
        val alertDialog = AlertDialog.Builder(this@RestaurantActivity).apply {
            setTitle("B???n c?? ch???c kh??ng ?")
            setMessage("B???n c?? mu???n x??a ${restaurant.Name} ra kh???i danh s??ch n??y kh??ng?")
            setPositiveButton("?????ng ??", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    viewModel.removeRestaurantItem(restaurant)
                    adapterRestaurant.notifyDataSetChanged()
                }
            })
            setNegativeButton("Kh??ng", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
        }
        alertDialog.show()
    }
}