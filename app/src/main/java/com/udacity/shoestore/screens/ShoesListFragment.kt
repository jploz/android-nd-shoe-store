package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesListBinding
import com.udacity.shoestore.databinding.ListitemShoeBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber


class ShoesListFragment : Fragment() {

    private val model: ShoesViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentShoesListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_shoes_list, container, false
        )

        model.shoes.observe(viewLifecycleOwner, Observer {
            for (item in it) {
                Timber.i("${item}")
                var itemBinding: ListitemShoeBinding = DataBindingUtil.inflate(
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

        return binding.root
    }
}
