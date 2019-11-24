package com.example.rpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kotlinx.android.synthetic.main.main_game_screen.*

class main_game_activity : AppCompatActivity() {
    lateinit var  enemy1: Architecture
    lateinit var  enemy2: Architecture
    lateinit var  player: Architecture

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_game_screen)
        val userInput = getIntent().getStringExtra("selectedArch");
        createPlayer(userInput)

        createEnemies()
    }

    fun createEnemies() {
        enemy1 = Tank(getString(R.string.enemy1Name))
        enemy1Health.text = enemy1.health.toString()
        enemy2 = Mage(getString(R.string.enemy1Name))
        enemy2Health.text = enemy2.health.toString()
    }

    fun createPlayer(archType: String) {
        if(archType == "mage") {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.mage)
        }
        else if(archType == "tank") {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.tank)
        }
        else {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.healer)
        }
    }

}
