package com.example.covisocials.adapters

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covisocials.databinding.ActivityLoginBinding
import com.example.covisocials.models.Posts

class PostsAdapter(val context:Context, val posts:List<Posts>) :
    RecyclerView.Adapter<PostsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return posts.size
    }


    inner class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(post: Posts){
            
        }
    }
}