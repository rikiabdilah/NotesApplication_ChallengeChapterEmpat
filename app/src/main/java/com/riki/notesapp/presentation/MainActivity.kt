package com.riki.notesapp.presentation

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.riki.notesapp.R
import com.riki.notesapp.data.NotePreferences

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_graph_container) as NavHostFragment

        navController = navHostFragment.navController


        val appBarConfiguration = AppBarConfiguration.Builder(
            R.id.splashScreenFragment,
            R.id.homeFragment
        ).build()

        setupActionBarWithNavController(navController, appBarConfiguration)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> {
                    this.supportActionBar?.show()
                    this.supportActionBar?.title = "Welcome to NotesApp"
                }
                R.id.splashScreenFragment -> this.supportActionBar?.hide()
                else -> {}
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.ascending -> {
                NotePreferences(this).put("KEY_FILTER", "ASCENDING")
                this.navController.navigate(R.id.homeFragment)
                return true
            }
            R.id.descending -> {
                NotePreferences(this).put("KEY_FILTER", "DESCENDING")
                this.navController.navigate(R.id.homeFragment)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}