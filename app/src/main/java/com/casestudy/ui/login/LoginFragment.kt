package com.casestudy.ui.login

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.casestudy.R
import com.casestudy.databinding.FragmentLoginBinding
import com.casestudy.model.User
import com.casestudy.ui.BaseFragment
import com.casestudy.util.Validator

class LoginFragment: BaseFragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.contBtn.setOnClickListener {
            loginControl()
        }

        binding.usernameEText.doOnTextChanged { _, _, _, _ ->
            binding.usernameInputLayout.isErrorEnabled = false
        }

        binding.passwordEText.doOnTextChanged { _, _, _, _ ->
            binding.passwordInputLayout.isErrorEnabled = false
        }

    }

    private fun loginControl(){

        val username = binding.usernameEText.editableText.toString()

        if(Validator.isUsernameValid(username).not()){
            binding.usernameInputLayout.error = getString(R.string.error_username)
            binding.usernameInputLayout.isErrorEnabled = true
            return
        }

        val password = binding.passwordEText.editableText.toString()
        if (Validator.isPasswordValid(password).not()){
            binding.passwordInputLayout.error = getString(R.string.error_password)
            binding.passwordInputLayout.isErrorEnabled = true
            return
        }

        User.userName = username
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)

        closeKeyboard()
    }

    private fun closeKeyboard(){
        val imm: InputMethodManager = requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}