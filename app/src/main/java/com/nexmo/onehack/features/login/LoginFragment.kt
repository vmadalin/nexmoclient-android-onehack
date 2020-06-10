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

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.nexmo.base.android.BaseFragment
import com.nexmo.base.android.annotations.OpenForTesting
import com.nexmo.onehack.R
import com.nexmo.onehack.databinding.FragmentLoginBinding

@OpenForTesting
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>(
    layoutId = R.layout.fragment_login
) {

    override val viewModel by viewModels<LoginViewModel> {
        LoginViewModelFactory()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.viewModel = viewModel
    }
}
