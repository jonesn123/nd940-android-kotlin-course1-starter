package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding

class ShoeDetailFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailBinding
    lateinit var viewModel: ShoeDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this).get(ShoeDetailViewModel::class.java)
        viewModel.error.observe(viewLifecycleOwner, { error ->
            if (error) {
                Toast.makeText(
                    context,
                    getString(R.string.toast_error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        binding.viewModel = viewModel

        binding.cancelBtn.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeFragment(null))
        }
        binding.saveBtn.setOnClickListener { view ->
            val shoe = viewModel.getShoe()
            if (shoe != null) {
                view.findNavController().navigate(
                    ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeFragment(shoe)
                )
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
}