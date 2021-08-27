package com.example.covisocials

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covisocials.adapters.PostsAdapter
import com.example.covisocials.databinding.ActivityPostsBinding
import com.example.covisocials.models.Posts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PostsActivity : AppCompatActivity() {
    private lateinit var fireStoreDb : FirebaseFirestore
    private lateinit var binding: ActivityPostsBinding
    private lateinit var posts : MutableList<Posts>
    private lateinit var postsAdapter : PostsAdapter
    private  val TAG = "PostsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        posts = mutableListOf()
        postsAdapter = PostsAdapter(this, posts)
        binding.rv.adapter = postsAdapter
        binding.rv.layoutManager = LinearLayoutManager(this)

        fireStoreDb = FirebaseFirestore.getInstance()
        val postsNode = fireStoreDb.collection("posts")

        postsNode.addSnapshotListener { value, error ->
            if (error!=null || value == null) {
                Toast.makeText(this, "Something gone wrong!", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            var postList = value.toObjects(Posts::class.java)
            posts.clear()
            posts.addAll(postList)
            postsAdapter.notifyDataSetChanged()
//            for (post in postList){
//                Log.i(TAG, "Post ${post}")
//            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.prof_menu){
            startActivity(Intent(this, ProfileActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }
}