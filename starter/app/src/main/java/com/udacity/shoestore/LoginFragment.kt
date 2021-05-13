package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ).apply {
            signinBtn.setOnClickListener { view ->
                if (validateLogin()) {
                    view.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                            idEdit.text.toString(),
                            false
                        )
                    )
                } else {
                    Toast.makeText(view.context, getText(R.string.fill_in_the_login_information), Toast.LENGTH_SHORT).show()
                }
            }
            signupBtn.setOnClickListener { view ->
                if (validateLogin()) {
                    view.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                            idEdit.text.toString(),
                            true
                        )
                    )
                } else {
                    Toast.makeText(view.context, getText(R.string.fill_in_the_login_information), Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    private fun validateLogin() = binding.run {
        idEdit.text.isNotEmpty() && pwEdit.text.isNotEmpty()
    }
}