package com.dot7.firebase.codelabs.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.dot7.firebase.R
import com.dot7.firebase.codelabs.models.Game
import com.google.firebase.database.*

class DataBaseActivity : AppCompatActivity() {
    lateinit var databaseReference: FirebaseDatabase
    lateinit var tvName: TextView
    lateinit var tvScore: TextView
    lateinit var tvUidd: TextView
    lateinit var btnSend: Button
    lateinit var imgCover: ImageView
    lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_base)
        initAll()
    }

    private fun initAll() {
        databaseReference = FirebaseDatabase.getInstance()
        myRef = databaseReference.getReference("games-won") //nodo padre

        tvName = findViewById(R.id.tv_data_name)
        tvScore = findViewById(R.id.tv_data_name)
        tvUidd = findViewById(R.id.tv_data_name)
        btnSend = findViewById(R.id.btn_send)
        imgCover = findViewById(R.id.iv_cover)
        btnSend.setOnClickListener { sendData() }
        readData()

    }

    private fun sendData() {
        val game = Game(
            name = "Basketball",
            score = 34,
            photoUrl = "https://tuguiatv.net/wp-content/uploads/2018/11/NBA-800x467.jpg"
        )
        val myRef = databaseReference.getReference("games-won") //nodo padre
        myRef.child("info").setValue(game)
    }

    private fun readData() {
      //  myRef = databaseReference.getReference("games-won").child("datos").child("info")
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val value = dataSnapshot.getValue(Game::class.java)
                Glide.with(this@DataBaseActivity)
                    .load(value?.photoUrl).into(imgCover)
            }

            override fun onCancelled(dataBaseError: DatabaseError) {
                Log.v("xxxError", "${dataBaseError.message}")
            }

        })
    }
}