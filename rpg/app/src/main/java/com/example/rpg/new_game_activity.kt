package com.example.rpg

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.new_game_screen.*

class new_game_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game_screen)
    }

    fun selectMage(v: View) {
        val text = findViewById<View>(R.id.nameInput) as EditText
        val playerName = text.text.toString()
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        myIntent.putExtra("selectedArch", "mage")
        myIntent.putExtra("playerName", playerName)
        startActivity(myIntent)
    }

    fun selectTank(v: View) {
        val text = findViewById<View>(R.id.nameInput) as EditText
        val playerName = text.text.toString()
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        myIntent.putExtra("selectedArch", "tank")
        myIntent.putExtra("playerName", playerName)
        startActivity(myIntent)
    }

    fun selectHealer(v: View) {
        val text = findViewById<View>(R.id.nameInput) as EditText
        val playerName = text.text.toString()
        val myIntent = Intent(baseContext, main_game_activity::class.java)
        myIntent.putExtra("selectedArch", "healer")
        myIntent.putExtra("playerName", playerName)
        startActivity(myIntent)
    }

}
