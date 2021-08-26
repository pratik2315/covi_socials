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
import com.example.covisocials.databinding.ActivityPostsBinding
import com.example.covisocials.models.Posts
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class PostsActivity : AppCompatActivity() {
    private lateinit var fireStoreDb : FirebaseFirestore
    private lateinit var binding: ActivityPostsBinding
    private  val TAG = "PostsActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostsBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_posts)

        fireStoreDb = FirebaseFirestore.getInstance()
        val postsNode = fireStoreDb.collection("posts")
            .orderBy("current_time", Query.Direction.DESCENDING)

        postsNode.addSnapshotListener { value, error ->
            if (error!=null || value == null) {
                Toast.makeText(this, "Something gone wrong!", Toast.LENGTH_SHORT).show()
                return@addSnapshotListener
            }
            var postList = value.toObjects(Posts::class.java)
            for (post in postList){
                Log.i(TAG, "Post ${post}")
            }
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