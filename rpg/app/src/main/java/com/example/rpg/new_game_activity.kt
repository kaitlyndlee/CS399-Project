package com.example.rpg

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.new_game_screen.*

class new_game_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game_screen)
    }

    fun selectMage(v: View) {
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        startActivity(myIntent)
    }

    fun selectTank(v: View) {
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        startActivity(myIntent)
    }

    fun selectHealer(v: View) {
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        startActivity(myIntent)
    }

}
