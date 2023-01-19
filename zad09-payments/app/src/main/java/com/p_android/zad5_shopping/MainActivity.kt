package com.p_android.zad5_shopping

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.p_android.zad5_shopping.payments.ShoppingCartActivity

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val TAG = "test"

    private lateinit var auth: FirebaseAuth

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    private fun createSignInIntent(): Intent {
        // [START auth_fui_create_intent]
        // Choose authentication providers
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build(),
            AuthUI.IdpConfig.GitHubBuilder().build(),
        )
        return AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        // [END auth_fui_create_intent]
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode != RESULT_OK) {
            val intent = Intent()
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
        if (auth.currentUser == null) {
            signInLauncher.launch(createSignInIntent())
        }
        supportFragmentManager.beginTransaction().apply {
            add(R.id.fcView, ListFragment.newInstance("a", "b"))
            commit()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)
        val cartButton = menu?.findItem(R.id.action_shopping_cart)
        cartButton?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_shopping_cart_24)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_shopping_cart -> {
                println("test1")
                val intent = Intent(this, ShoppingCartActivity::class.java)
                println("test2")
                startActivity(intent)
                println("test3")
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}