package com.udacity.shoestore.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentAddShoeBinding
import com.udacity.shoestore.models.ShoesViewModel
import timber.log.Timber

class AddShoeFragment : Fragment() {

    private val model: ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentAddShoeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_shoe, container, false
        )

        binding.shoe = ShoesViewModel.makeShoeInstance()

        binding.saveBtn.setOnClickListener {
            Timber.i("Save new shoe: ${binding.shoe}")
            model.addShoe(binding.shoe!!)
            findNavController().navigateUp()
        }

        binding.cancelBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }
}
