package com.example.covisocials

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.covisocials.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null){
            startPostActivity()
        }

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            val userEmail = binding.etEmail.text.toString()
            val userPassword = binding.etPassword.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(this, "Input fields Cannot be Empty!", Toast.LENGTH_SHORT).show()
                binding.btnLogin.isEnabled = true
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
                binding.btnLogin.isEnabled = true
                if (task.isSuccessful){
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                    startPostActivity()
                } else{
                    Toast.makeText(this, "Task Failed!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun startPostActivity(){
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, PostsActivity::class.java))
        finish()
    }
}