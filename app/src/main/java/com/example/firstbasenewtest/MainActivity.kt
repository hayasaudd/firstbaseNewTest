package com.example.firstbasenewtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()) { res ->
        this.onSignInResult(res)
    }
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val b = findViewById<Button>(R.id.button)


        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        if (FirebaseAuth.getInstance().currentUser == null) {
            b.setOnClickListener { signInLauncher.launch(signInIntent) }
        }else
        {
            b.text = "AignOut"
            b.setOnClickListener { AuthUI.getInstance().signOut(this) }
        }


    }



    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            // Successfully signed in
            val user = FirebaseAuth.getInstance().currentUser
            // ...
        } else {
            // Sign in failed. If response is null the user canceled the
            // sign-in flow using the back button. Otherwise check
            // response.getError().getErrorCode() and handle the error.
            // ...
        }
    }

}