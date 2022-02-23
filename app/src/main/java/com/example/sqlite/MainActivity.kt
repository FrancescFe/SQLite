package com.example.sqlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var friendsDBHelper = mySQLiteHelper(this)

        binding.btnSave.setOnClickListener {
            friendsDBHelper.addData(binding.etName.text.toString(),
                binding.etEmail.text.toString())
        }

        binding.btnConsult.setOnClickListener {
            binding.tvConsult.text = ""
            val cursor = friendsDBHelper.showData()
            if(cursor!!.moveToFirst()){
                do {
                    binding.tvConsult.append(cursor.getInt(0).toString() + ": ")
                    binding.tvConsult.append(cursor.getString(1).toString() + ", ")
                    binding.tvConsult.append(cursor.getString(2).toString() + "\n")
                } while (cursor.moveToNext())
            }
        }

        binding.btnUpdateDelete.setOnClickListener {
            var intent = Intent(this, UpdateDelete::class.java)
            startActivity(intent)
        }

    }
}