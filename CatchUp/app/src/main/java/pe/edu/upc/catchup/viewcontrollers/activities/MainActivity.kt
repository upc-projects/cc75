package pe.edu.upc.catchup.viewcontrollers.activities

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import pe.edu.upc.catchup.R
import pe.edu.upc.catchup.models.SettingsRepository
import pe.edu.upc.catchup.viewcontrollers.fragments.FavoritesFragment
import pe.edu.upc.catchup.viewcontrollers.fragments.HomeFragment
import pe.edu.upc.catchup.viewcontrollers.fragments.SettingsFragment
import pe.edu.upc.catchup.viewcontrollers.fragments.SourcesFragment

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        return@OnNavigationItemSelectedListener navigateTo(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.navigation_home
        val settings = SettingsRepository(this)
        if (settings.shouldShowOnboarding) {
            startActivity(
                    Intent(this,
                            OnboardingActivity::class.java))
        }
    }

    private fun fragmentFor(item: MenuItem): Fragment {
        when(item.itemId) {
            R.id.navigation_home -> {
                return HomeFragment()
            }
            R.id.navigation_sources -> {
                return SourcesFragment()
            }
            R.id.navigation_favorites -> {
                return FavoritesFragment()
            }
            R.id.navigation_settings -> {
                return SettingsFragment()
            }
        }
        return HomeFragment()
    }

    private fun navigateTo(item: MenuItem): Boolean {
        item.setChecked(true)
        return supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragmentFor(item))
                .commit() > 0

    }
}
