package com.example.covisocials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.covisocials.databinding.ActivityLoginBinding
import com.example.covisocials.databinding.ActivityPostsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileActivity : PostsActivity() {


    //inflates the logout menu i created in the menu folder
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_logout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout_click){
            FirebaseAuth.getInstance().signOut() //performs signout
            val logoutIntent = Intent(this, LoginActivity::class.java)
            logoutIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //some flags to remove previous activities frm the stack
            startActivity(logoutIntent)
        }
        return super.onOptionsItemSelected(item)
    }
}