package com.example.rpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startGameButton.setOnClickListener{
            //swap layouts/activities to start game
            setContentView(R.layout.new_game_screen)
        }
    }
}
