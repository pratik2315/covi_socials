package com.example.covisocials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.covisocials.databinding.ActivityLoginBinding
import com.example.covisocials.databinding.ActivityRegistrationBinding
import com.example.covisocials.models.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var fireStoreDb : FirebaseFirestore
    private var signedInUser : Users?= null
    private val TAG = "RegistrationActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()
        fireStoreDb = FirebaseFirestore.getInstance()

        binding.btnSignUp.setOnClickListener {
            binding.btnSignUp.isEnabled = false
            val userEmail = binding.etRegEmail.text.toString()
            val userPassword = binding.etRegPassword.text.toString()
            val username = binding.etUsername.text.toString()
            val age = binding.etAge.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(this, "Input fields Cannot be Empty!", Toast.LENGTH_SHORT).show()
                binding.btnSignUp.isEnabled = true
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
                binding.btnSignUp.isEnabled = true
                if (task.isSuccessful){

                    var userId = auth.currentUser?.uid as String //gets the unique user id from the registered user
                    var documentReference = fireStoreDb.collection("users").document(userId) //points to the "users" collection in fstore

                    val user:HashMap<String, String> = HashMap<String, String>()
                    user.put("username", username)
                    user.put("age", age) //collects the username and age field from the user

                    //and sets those values to the firestore values
                    documentReference.set(user).addOnSuccessListener {
                        Log.i(TAG, "user id: $userId")
                    }.addOnFailureListener{ exception ->
                        Log.i(TAG, "Error: $exception")
                    }
                    startPostsActivity()
                } else{
                    Toast.makeText(this, "Task Failed!", Toast.LENGTH_SHORT).show()
                }
            }

        }

        binding.imgBackArrow.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    private fun startPostsActivity() {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

}