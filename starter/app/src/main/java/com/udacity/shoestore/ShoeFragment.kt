package com.udacity.shoestore

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.FragmentShoeBinding
import com.udacity.shoestore.databinding.LayoutShoeBinding

class ShoeFragment : Fragment() {

    lateinit var activityViewMode: ShoeViewModel
    lateinit var binding: FragmentShoeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe,
            container,
            false
        )

        binding.fab.setOnClickListener { view ->
            view.findNavController()
                .navigate(ShoeFragmentDirections.actionShoeFragmentToShoeDetailFragment())
        }

        activityViewMode = (activity as MainActivity).viewModel
        activityViewMode.shoes.observe(viewLifecycleOwner, { shoes ->
            binding.shoeContainer.removeAllViews()
            shoes.forEach { shoe ->
                DataBindingUtil.inflate<LayoutShoeBinding>(
                    layoutInflater,
                    R.layout.layout_shoe,
                    binding.shoeContainer,
                    true
                ).apply {
                    item = shoe
                }
            }
        })
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activityViewMode.fetchShoes()
    }

    override fun onResume() {
        super.onResume()
        val args = ShoeFragmentArgs.fromBundle(requireArguments())

        args.shoe?.let {
            activityViewMode.addShoe(it)
        }
    }
}