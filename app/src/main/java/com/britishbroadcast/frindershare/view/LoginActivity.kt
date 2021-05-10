package com.britishbroadcast.frindershare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.britishbroadcast.frindershare.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            if(validLogin())
                loginUser()
        }
        val animation = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)


        sign_up_textview.setOnClickListener {

            it.animation = animation
            sign_up_view.animation = animation
            sign_up_view.visibility = View.VISIBLE

        }


        su_button.setOnClickListener {
            signUpNewUser()
        }

    }

    private fun signUpNewUser() {
        val email = su_email_edittext.text.toString().trim()
        val password = su_password_edittext.text.toString().trim()

        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {

                if(it.isSuccessful){
                    Toast.makeText(this, "Sign up complete. Please verify email link.", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().currentUser?.sendEmailVerification()
                    val animation2 = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right)

                    sign_up_view.animation = animation2
                    sign_up_view.visibility = View.GONE
                } else {
                    Toast.makeText(this, "Error! ${it.exception?.localizedMessage}", Toast.LENGTH_SHORT).show()
                }

            }
    }

    private fun validLogin(): Boolean {

        if(li_email_edittext.text.isEmpty()){

            Toast.makeText(this, "Email cannot be empty!", Toast.LENGTH_SHORT).show()
            return false

        } else if(li_password_edittext.text.isEmpty()){

            Toast.makeText(this, "Password cannot be empty!", Toast.LENGTH_SHORT).show()
            return false

        }

        return true

    }

    private fun loginUser() {

        val email = li_email_edittext.text.toString()
        val password = li_password_edittext.text.toString()

        FirebaseAuth.getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    if(FirebaseAuth.getInstance().currentUser?.isEmailVerified == true) {
                        Toast.makeText(this, "User authenticated!!", Toast.LENGTH_SHORT).show()

                        val intent1 = Intent(this, HomeActivity::class.java)
                        intent1.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent1)

                    }
                    else
                        Toast.makeText(this, "Please verify your EMAIL!!!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, it.exception?.localizedMessage ?: "oops!", Toast.LENGTH_SHORT).show()
                }
            }
    }


}