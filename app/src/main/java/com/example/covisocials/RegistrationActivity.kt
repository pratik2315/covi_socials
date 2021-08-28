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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val auth = FirebaseAuth.getInstance()

        binding.btnSignUp.setOnClickListener {
            binding.btnSignUp.isEnabled = false
            val userEmail = binding.etRegEmail.text.toString()
            val userPassword = binding.etRegPassword.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(this, "Input fields Cannot be Empty!", Toast.LENGTH_SHORT).show()
                binding.btnSignUp.isEnabled = true
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
                binding.btnSignUp.isEnabled = true
                if (task.isSuccessful){
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