package com.britishbroadcast.frindershare.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.britishbroadcast.frindershare.R
import com.britishbroadcast.frindershare.model.data.FrinderPost
import com.britishbroadcast.frindershare.view.adapter.PostAdapter
import com.britishbroadcast.frindershare.viewmodel.FrinderViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private val postAdapter = PostAdapter(mutableListOf())

    val viewModel: FrinderViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("TAG_X", "Logged...in.....")

        main_recyclerview.adapter = postAdapter

        viewModel.getPostData().observe(this, Observer {
            postAdapter.updatePosts(it)
        })

        logout_button.setOnClickListener {

            Log.d("TAG_X", "Logging out...")
            FirebaseAuth.getInstance().signOut()

            val lIntent = Intent(this, LoginActivity::class.java)
            lIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(lIntent)

        }

        add_post.setOnClickListener {
            val upload = UploadFragment()
            supportFragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .add(R.id.main_frame, upload)
                    .addToBackStack(upload.tag)
                    .commit()
        }



    }
}