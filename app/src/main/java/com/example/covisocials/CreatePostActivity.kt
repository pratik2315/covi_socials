package com.example.covisocials

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.covisocials.databinding.ActivityCreatePostBinding
import com.example.covisocials.databinding.ActivityPostsBinding
import com.example.covisocials.models.Posts
import com.example.covisocials.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.lang.Exception
import java.net.URI

class CreatePostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreatePostBinding
    private val TAG = "CreatePostsActivity"
    private val CODE = 1
    private var picUri : Uri ?= null
    private lateinit var fireStoreDb : FirebaseFirestore
    private lateinit var storageReference : StorageReference
    private var signedInUser : Users? = null
    private val USERNAME_KEY = "KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreatePostBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        fireStoreDb = FirebaseFirestore.getInstance()
        storageReference = FirebaseStorage.getInstance().reference

        fireStoreDb.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid as String)
                .get()
                .addOnSuccessListener { snapShot ->
                    signedInUser = snapShot.toObject(Users::class.java)
                    Log.i(TAG, "Post $signedInUser")
                }.addOnFailureListener{ exception->
                    Log.i(TAG, "Fail", exception)
                }

        binding.btnChooseImage.setOnClickListener {
            val galleryIntent = Intent(Intent.ACTION_GET_CONTENT)
            galleryIntent.type = "image/*"
            if (galleryIntent.resolveActivity(packageManager)!=null){
                startActivityForResult(galleryIntent, CODE)
            }
        }

        binding.btnSubmit.setOnClickListener {
            handleSubmitButton()
        }
    }

    private fun handleSubmitButton() {
        if (picUri == null){
            Toast.makeText(this, "No image selected!", Toast.LENGTH_SHORT).show()
        }
        if (signedInUser == null){
            Toast.makeText(this, "No user signed in!", Toast.LENGTH_SHORT).show()
        }

        try {
            val picUpUri = picUri as Uri

            val picReference = storageReference.child("images/${System.currentTimeMillis()}-photo.jpg")
            picReference.putFile(picUpUri)
                    .continueWithTask { photoUploadTask ->
                        picReference.downloadUrl
                    }
                    .continueWithTask {
                        val user = Users(
                                signedInUser?.username as String,
                                signedInUser?.age as Int
                        )
                        fireStoreDb.collection("users").add(user)
                    }
                    .continueWithTask { postDownloadUrl ->
                        val post = Posts(
                                binding.etCaption.text.toString(),
                                System.currentTimeMillis(),
                                postDownloadUrl.result.toString(),
                                signedInUser
                        )
                        fireStoreDb.collection("posts").add(post)
                    }.addOnCompleteListener { postCreationInDB ->
                        if (!postCreationInDB.isSuccessful) {
                            Toast.makeText(this, "Failed to upload photo to firebase storage", Toast.LENGTH_SHORT).show()
                        }

                        binding.etCaption.text.clear()
                        binding.imagePost.setImageResource(0)
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, ProfileActivity::class.java)
                        intent.putExtra(USERNAME_KEY, signedInUser?.username)
                        startActivity(intent)
                        finish()
                    }
        } catch (e: Exception){

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CODE){
            if (resultCode == Activity.RESULT_OK){
                picUri = data?.data
                binding.imagePost.setImageURI(picUri)
            } else{
                Toast.makeText(this, "Could not fetch image!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}