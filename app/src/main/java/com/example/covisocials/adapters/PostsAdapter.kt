package com.example.covisocials.adapters

import android.content.Context
import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.covisocials.R
import com.example.covisocials.databinding.ActivityLoginBinding
import com.example.covisocials.databinding.PostsItemBinding
import com.example.covisocials.models.Posts

class PostsAdapter(private val context:Context, private val posts:List<Posts>) :
    RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    //inlfates the post_items layout created in the layouts folder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.posts_item, parent, false)
        return PostsViewHolder(view)
    }

    //binds the data to the layout elements
    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val postList = posts[position]
        holder.name.text = postList.user?.username
        holder.time.text = DateUtils.getRelativeTimeSpanString(postList.current_time) //converts mseconds to current readable time
        holder.captionTv.text = postList.caption
        Glide.with(context).load(postList.image_url).into(holder.postImage)
    }

    //returns the size
    override fun getItemCount() = posts.size


    //accesses the layout elements with the help of "viewholder" class
    inner class PostsViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var name = view.findViewById<TextView>(R.id.userName)
        var time = view.findViewById<TextView>(R.id.timeTv)
        var captionTv = view.findViewById<TextView>(R.id.captionTv)
        var postImage = view.findViewById<ImageView>(R.id.postImage)
    }
}