package com.example.sqlite

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlite.databinding.ActivityUpdateDeleteBinding

class UpdateDelete : AppCompatActivity() {
    lateinit var binding: ActivityUpdateDeleteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnDelete.setOnClickListener {
            var friendsDBHelper = mySQLiteHelper(this)

            var delete : Int = 0
            var input = binding.etId.text.toString().trim()
            if (input.isNullOrBlank()){
                delete = 0
            } else {
                delete = friendsDBHelper.deleteData(binding.etId.text.toString().toInt())
            }

            println("--- " + delete)
            if (delete != 0){
                Toast.makeText(this, "success delete", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "failed delete, maybe missing fields", Toast.LENGTH_LONG).show()
            }
        }

        binding.btnUpdate.setOnClickListener {
            var amigosDBHelper = mySQLiteHelper(this)

            var update : Int = 0

            var input = binding.etId.text.toString().trim()
            if (input.isNullOrBlank()){

                update = amigosDBHelper.updateData(

                    0,
                    binding.etNewName.text.toString(),
                    binding.etNewEmail.text.toString()
                )
            } else {
                update = amigosDBHelper.updateData(

                    binding.etId.text.toString().toInt(),
                    binding.etNewName.text.toString(),
                    binding.etNewEmail.text.toString()
                )

            }

            println("--- " + update)
            if (update != 0){
                Toast.makeText(this, "success update", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "failed update, maybe missing fields", Toast.LENGTH_LONG).show()
            }
        }

    }
}