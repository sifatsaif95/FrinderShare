package com.britishbroadcast.frindershare.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.britishbroadcast.frindershare.model.data.FrinderPost
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FrinderRepository {

    companion object{
        const val POST_PATH = "Posts"
    }

    val postLiveData: MutableLiveData<List<FrinderPost>> = MutableLiveData()

    init {
        getAllPosts()
    }

    private fun getAllPosts(){

        FirebaseDatabase.getInstance().reference
            .child(POST_PATH) // path of variables in database you are reading..
            .addValueEventListener(
                object : ValueEventListener{
                    override fun onCancelled(error: DatabaseError) {
                        Log.d("TAG_X", "An error occurred! ${error.message}")
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val list = mutableListOf<FrinderPost>()
                        snapshot.children.forEach{

                            val frinderPost = it.getValue(FrinderPost::class.java)
                            frinderPost?.let {
                                list.add(frinderPost)
                            }

                            postLiveData.value = list
                        }
                    }
                }
            )
    }

    fun addFrinderPost(post: FrinderPost){
         FirebaseDatabase.getInstance().reference.child(POST_PATH)
            .push().setValue(post)
    }


}