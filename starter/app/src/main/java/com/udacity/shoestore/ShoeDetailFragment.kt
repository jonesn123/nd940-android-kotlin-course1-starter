package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe

class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.cancelBtn.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeFragment(null))
        }
        binding.saveBtn.setOnClickListener { view ->
            if (validateInput()) {
                view.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeFragment(createShoe())
                )
            } else {
                Toast.makeText(
                    view.context,
                    getString(R.string.toast_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(
            item,
            requireView().findNavController()
        ) || super.onOptionsItemSelected(item)
    }

    private fun createShoe(): Shoe = Shoe(
        name = binding.shoeNameEt.text.toString(),
        size = binding.shoeSizeEt.text.toString().toDouble(),
        company = binding.shoeCompanyEt.text.toString(),
        description = binding.shoeDescriptionEt.text.toString()
    )

    private fun validateInput(): Boolean = binding.run {
        shoeNameEt.text.isNotEmpty() && shoeCompanyEt.text.isNotEmpty() && shoeSizeEt.text.isNotEmpty() && shoeDescriptionEt.text.isNotEmpty()
    }
}