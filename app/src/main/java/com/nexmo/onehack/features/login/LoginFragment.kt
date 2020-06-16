/*
 * Copyright (C) 2020 Nexmo, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nexmo.onehack.features.login

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.extensions.observe
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentLoginBinding
import com.nexmo.onehack.features.calls.incoming.IncomingCallViewStates

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    layoutId = R.layout.fragment_login
) {

    companion object {
        private const val CALL_PERMISSIONS_REQUEST_ID = 123
        private val CALL_PERMISSIONS_ARRAY =
            arrayOf(Manifest.permission.READ_PHONE_STATE, Manifest.permission.RECORD_AUDIO)
    }

    override val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onStateChange)
        viewBinding.viewModel = viewModel
        requestCallPermissions()
    }

    private fun onStateChange(state: LoginViewState) {
        when (state) {
            LoginViewState.CONNECTED -> findNavController()
                .navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            LoginViewState.DISCONNECTED, LoginViewState.UNKNOWN ->
                Toast.makeText(
                    requireContext(),
                    R.string.feature_login_error_text,
                    Toast.LENGTH_SHORT
                ).show()
            else -> {}
        }
    }

    private fun requestCallPermissions() {
        CALL_PERMISSIONS_ARRAY
            .filter {
                ActivityCompat.checkSelfPermission(
                    requireActivity(),
                    it
                ) != PackageManager.PERMISSION_GRANTED
            }
            .onEach {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    CALL_PERMISSIONS_ARRAY,
                    CALL_PERMISSIONS_REQUEST_ID
                )
            }
    }
}
