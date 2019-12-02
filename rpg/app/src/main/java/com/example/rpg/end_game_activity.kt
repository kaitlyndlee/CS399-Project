package com.example.rpg


import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.end_game_screen.*


class end_game_activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "GOT HERE IN END GAME ACTIVITY")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.end_game_screen)
        endGame.text = getIntent().getStringExtra("result")
    }
}
