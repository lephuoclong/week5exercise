package com.example.secondexercise.features.home

import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.secondexercise.R
import com.example.secondexercise.adapters.RestaurantsAdapter
import com.example.secondexercise.databinding.FragmentHomeBinding
import com.example.secondexercise.models.Restaurant
import com.example.secondexercise.models.User
import com.example.secondexercise.utils.DataStore
import com.example.secondexercise.viewmodels.HomeViewModel

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapterRestaurant: RestaurantsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).supportActionBar?.show()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.getListRestaurant(requireContext())
        viewModel.listRestaurant.observe(viewLifecycleOwner, Observer {list->
            viewModel.menuOptions.observe(viewLifecycleOwner, Observer { option ->
                setUpRecyclerView(list, option)
            })
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_select_layout, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_listView -> if(viewModel.CURRENT_OPTION!= viewModel.LISTVIEW_OPTION) {
                viewModel.onChangeMenu(false)
                viewModel.setValueToCurrentOption(viewModel.LISTVIEW_OPTION)
            }
            R.id.menu_gridView -> if(viewModel.CURRENT_OPTION!=viewModel.GRIDVIEW_OPTION){
                viewModel.onChangeMenu(true)
                viewModel.setValueToCurrentOption(viewModel.GRIDVIEW_OPTION)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setUpRecyclerView(list:ArrayList<Restaurant>, option:Boolean) {
        adapterRestaurant = RestaurantsAdapter(requireContext(), object : RestaurantsAdapter.ClickItem{
            override fun clickItem(restaurant: Restaurant) {
                showAlertDialogDeleteRestaurant(restaurant)
            }
        })
        adapterRestaurant.submitList(list)
        binding.rcvRestaurant.layoutManager = if(!option) LinearLayoutManager(requireContext()) else GridLayoutManager(requireContext(), 2)
        binding.rcvRestaurant.setHasFixedSize(true)
        binding.rcvRestaurant.itemAnimator = DefaultItemAnimator()
        binding.rcvRestaurant.addItemDecoration(DividerItemDecoration( binding.rcvRestaurant.context, DividerItemDecoration.VERTICAL))
        binding.rcvRestaurant.adapter = adapterRestaurant
    }
    private fun showAlertDialogDeleteRestaurant(restaurant: Restaurant){
        val alertDialog = AlertDialog.Builder(requireContext()).apply {
            setTitle("Bạn có chắc không ?")
            setMessage("Bạn có muốn xóa ${restaurant.Name} ra khỏi danh sách này không?")
            setPositiveButton("Đồng ý", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    viewModel.removeRestaurantItem(restaurant)
                    adapterRestaurant.notifyDataSetChanged()
                }
            })
            setNegativeButton("Không", object : DialogInterface.OnClickListener{
                override fun onClick(p0: DialogInterface?, p1: Int) {}
            })
        }
        alertDialog.show()
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        binding.btnProfile.setOnClickListener {
            val controller = findNavController()
            controller.navigate(R.id.action_homeFragment_to_profileFragment)
        }
    }
}