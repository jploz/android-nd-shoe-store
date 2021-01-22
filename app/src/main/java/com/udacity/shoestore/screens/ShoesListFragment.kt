package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ListitemShoeBinding
import com.udacity.shoestore.models.ShoesViewModel


class ShoesListFragment : Fragment() {

    private val model: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentShoesListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes_list, container, false
        )

        model.shoes.observe(viewLifecycleOwner, {
            if (it.size > 0) {
                binding.shoesListEmptyText.visibility = View.GONE
            } else {
                binding.shoesListEmptyText.visibility = View.VISIBLE
            }

            // in order to not add items multiple times, remove all items form the
            // linear layout first (note: this is not the best way to implement a list view)
            binding.shoesListLayout.removeAllViews()
            for (item in it) {
                val itemBinding: ListitemShoeBinding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.listitem_shoe,
                    binding.shoesListLayout,
                    true
                )
                itemBinding.shoe = item
            }
        })

        binding.addShoeFab.setOnClickListener {
            findNavController().navigate(R.id.action_shoesListFragment_to_shoeDetailFragment)
        }

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoes_list_overflow_menu, menu)
        menu.findItem(R.id.loadPredefinedData).isEnabled = !model.predefinedDataIsLoaded
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.loadPredefinedData -> {
                model.addPredefinedShoes()
                item.isEnabled = !model.predefinedDataIsLoaded
                return true
            }
            R.id.clearShoeList -> {
                model.clearShoesList()
                return true
            }
        }

        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }
}
