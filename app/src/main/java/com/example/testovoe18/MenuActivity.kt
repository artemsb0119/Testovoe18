package com.example.testovoe18

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.testovoe18.databinding.ActivityMenuBinding
import com.google.android.material.tabs.TabLayout

class MenuActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = findViewById(R.id.tabLayout)

        val tab1: TabLayout.Tab = tabLayout.newTab()
        tab1.text = "Main"
        tab1.setIcon(R.drawable.home)
        tabLayout.addTab(tab1)

        val tab2: TabLayout.Tab = tabLayout.newTab()
        tab2.text = "History"
        tab2.setIcon(R.drawable.arrows)
        tabLayout.addTab(tab2)

        val tab3: TabLayout.Tab = tabLayout.newTab()
        tab3.text = "Budget"
        tab3.setIcon(R.drawable.diagram)
        tabLayout.addTab(tab3)

        val tab4: TabLayout.Tab = tabLayout.newTab()
        tab4.text = "Account"
        tab4.setIcon(R.drawable.people)
        tabLayout.addTab(tab4)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val position = tab.position
                val fragmentTransaction = supportFragmentManager.beginTransaction()

                when (position) {
                    3 -> {
                        val profileFragment = ProfileFragment()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, profileFragment)
                            .commit()
                    }
                    1 -> {
                        val historyFragment = History()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, historyFragment)
                            .commit()
                    }
                    0 -> {
                        val homeFragment = Main()
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.frame_layout, homeFragment)
                            .commit()
                    }
                }

                fragmentTransaction.addToBackStack(null)
                fragmentTransaction.commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        replaceFragment(Main())
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.frame_layout, Main(), "MainFragment")
        fragmentTransaction.commit()
    }
}
