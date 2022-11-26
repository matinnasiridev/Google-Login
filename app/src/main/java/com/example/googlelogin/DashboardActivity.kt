package com.example.googlelogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.googlelogin.databinding.ActivityDashboardBinding
import com.google.firebase.auth.FirebaseAuth

class DashboardActivity : AppCompatActivity() {
    lateinit var binding: ActivityDashboardBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser

        binding.txtName.text = currentUser?.displayName
        binding.txtEmail.text = currentUser?.email
        binding.txtId.text = currentUser?.uid
        Glide
            .with(this)
            .load(currentUser?.photoUrl)
            .into(binding.profile)

        binding.btnOut.setOnClickListener {
            mAuth.signOut()
            val goLogin = Intent(this@DashboardActivity, LoginActivity::class.java)
            startActivity(goLogin)
            finish()
        }


    }
}