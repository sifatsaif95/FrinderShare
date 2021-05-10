package com.britishbroadcast.frindershare.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.britishbroadcast.frindershare.R
import com.britishbroadcast.frindershare.model.data.FrinderPost
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.post_item_layout.view.*

class PostAdapter(var frinderPosts: List<FrinderPost>): RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item_layout, parent, false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int = frinderPosts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val frinderPost = frinderPosts[position]
        holder.itemView.apply {
            post_description.text = frinderPost.description

            Glide.with(this)
                    .setDefaultRequestOptions(RequestOptions().centerInside())
                    .load(frinderPost.imageUrl)
                    .into(post_imageview)
        }
    }

    fun updatePosts(posts: List<FrinderPost>) {
        frinderPosts = posts
        notifyDataSetChanged()
    }
}