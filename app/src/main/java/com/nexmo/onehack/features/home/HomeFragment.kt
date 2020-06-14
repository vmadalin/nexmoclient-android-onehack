package com.nexmo.onehack.features.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.nexmo.onehack.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homeNavHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_container) as NavHostFragment
        bottom_navigation.setupWithNavController(homeNavHostFragment.navController)
    }
}
