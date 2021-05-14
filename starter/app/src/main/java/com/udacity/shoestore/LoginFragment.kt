package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginViewModel.error.observe(viewLifecycleOwner, { error ->
            if (error) {
                Toast.makeText(
                    context,
                    getText(R.string.fill_in_the_login_information),
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

        binding = DataBindingUtil.inflate<FragmentLoginBinding>(
            inflater,
            R.layout.fragment_login,
            container,
            false
        ).apply {
            viewModel = loginViewModel
            signinBtn.setOnClickListener { view ->
                loginViewModel.sign()?.let { id ->
                    view.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                            id,
                            false
                        )
                    )
                }
            }
            signupBtn.setOnClickListener { view ->
                loginViewModel.sign()?.let { id ->
                    view.findNavController().navigate(
                        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(
                            id,
                            true
                        )
                    )
                }
            }
        }
        return binding.root
    }

}