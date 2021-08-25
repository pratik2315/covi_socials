package com.example.covisocials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.covisocials.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val userEmail = binding.etEmail.text.toString()
            val userPassword = binding.etPassword.text.toString()
            if (userEmail.isBlank() || userPassword.isBlank()){
                Toast.makeText(this, "Input fields Cannot be Empty!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val auth = FirebaseAuth.getInstance()
            auth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                } else{
                    Toast.makeText(this, "Task did not go well!", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }
}