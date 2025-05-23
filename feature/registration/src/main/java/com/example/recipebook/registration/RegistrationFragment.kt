package com.example.recipebook.registration

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.recipebook.BaseFragment
import com.example.recipebook.base.R
import com.example.recipebook.registration.databinding.FragmentRegistrationBinding
import com.example.recipebook.utils.hideKeyboard
import com.example.recipebook.utils.setupPasswordToggle
import com.example.recipebook.utils.setupValidation
import com.example.recipebook.utils.ValidationUtils
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.example.recipebook.registration.R as registerR


@AndroidEntryPoint
class RegistrationFragment : BaseFragment(registerR.layout.fragment_registration) {
    private val viewBinding: FragmentRegistrationBinding by viewBinding(FragmentRegistrationBinding::bind)
    private val viewModel: RegistrationViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupPasswordToggle()
        setupRealTimeValidation()
        setupListeners()
        setupObservers()
    }

    private fun setupPasswordToggle() {
        viewBinding.textInputLayoutPassword.setupPasswordToggle(viewBinding.etPassword)
        viewBinding.textInputLayoutPasswordRepeat.setupPasswordToggle(viewBinding.etPasswordRepeat)
    }

    private fun setupRealTimeValidation() {
        viewBinding.etPhone.setupValidation(
            ValidationUtils::isValidPhone,
            viewBinding.textInputLayoutPhone,
            R.string.error_invalid_phone
        )

        viewBinding.etPassword.setupValidation(
            ValidationUtils::isValidPassword,
            viewBinding.textInputLayoutPassword,
            R.string.error_invalid_password
        )

        viewBinding.etPasswordRepeat.doOnTextChanged { text, _, _, _ ->
            val password = viewBinding.etPassword.text.toString()
            if (text.toString() != password) {
                viewBinding.textInputLayoutPasswordRepeat.error = getString(R.string.error_password_mismatch)
            } else {
                viewBinding.textInputLayoutPasswordRepeat.error = null
            }
        }
    }

    private fun setupListeners() {
        viewBinding.etPassword.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                attemptRegister()
                return@setOnEditorActionListener true
            }
            false
        }

        viewBinding.ivBackIcon.setOnClickListener {
            hideKeyboard()
            viewModel.navigateToAuthorization()
        }

        viewBinding.btnLogin.setOnClickListener {
            attemptRegister()
        }
    }

    private fun attemptRegister() {
        val phone = viewBinding.etPhone.text.toString()
        val password = viewBinding.etPassword.text.toString().trim()
        val confirmPassword = viewBinding.etPasswordRepeat.text.toString().trim()

        val isPhoneValid = validateField(
            phone,
            ValidationUtils::isValidPhone,
            viewBinding.textInputLayoutPhone,
            R.string.error_invalid_phone
        )

        val isPasswordValid = validateField(
            password,
            ValidationUtils::isValidPassword,
            viewBinding.textInputLayoutPassword,
            R.string.error_invalid_password
        )

        val isPasswordMatch = if (password != confirmPassword) {
            viewBinding.textInputLayoutPasswordRepeat.error = getString(R.string.error_password_mismatch)
            false
        } else {
            viewBinding.textInputLayoutPasswordRepeat.error = null
            true
        }

        if (isPhoneValid && isPasswordValid && isPasswordMatch) {
            viewModel.register(phone, password)
        }
    }

    private fun validateField(
        value: String,
        validator: (String) -> Boolean,
        textInputLayout: TextInputLayout?,
        errorResId: Int
    ): Boolean {
        return if (!validator(value)) {
            textInputLayout?.error = getString(errorResId)
            false
        } else {
            textInputLayout?.error = null
            true
        }
    }

    private fun setupObservers() {
        viewModel.uiState
            .onEach { state ->
                when (state) {
                    RegistrationViewModel.RegistrationUiState.Loading -> showProgress()
                    RegistrationViewModel.RegistrationUiState.Idle -> hideProgress()
                    RegistrationViewModel.RegistrationUiState.Success -> hideProgress()
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)

        viewModel.events
            .onEach { event ->
                when (event) {
                    is RegistrationViewModel.RegistrationEvent.ShowError -> {
                        handleErrors(event.error)
                    }
                }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleErrors(error: RegistrationViewModel.RegistrationError) {
        val message = when (error) {
            RegistrationViewModel.RegistrationError.UserAlreadyExists -> R.string.error_user_already_exists
            RegistrationViewModel.RegistrationError.Unknown -> R.string.error_unknown
        }
        showToast(getString(message))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
