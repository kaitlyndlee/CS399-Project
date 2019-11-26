package com.example.rpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.main_game_screen.*
import kotlin.random.Random

class main_game_activity : AppCompatActivity() {
    lateinit var  enemy1: Architecture
    lateinit var  enemy2: Architecture
    lateinit var  playerArch: Architecture

    private val imageViews by lazy {
        arrayOf<ImageView>(
            findViewById(R.id.enemy1),
            findViewById(R.id.enemy2)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_game_screen)
        val userInput = getIntent().getStringExtra("selectedArch")
        val playerName = getIntent().getStringExtra("playerName")
        createPlayer(userInput, playerName)

        createEnemies()

        attackButton.setOnClickListener{
            runRound("attack")
            characterHealth.text = playerArch.health.toString()
            enemy1Health.text = enemy1.health.toString()
            enemy2Health.text = enemy2.health.toString()
        }

        healButton.setOnClickListener{
            runRound("heal")
            characterHealth.text = playerArch.health.toString()
            enemy1Health.text = enemy1.health.toString()
            enemy2Health.text = enemy2.health.toString()
        }

        blockButton.setOnClickListener{
            runRound("block")
            characterHealth.text = playerArch.health.toString()
            enemy1Health.text = enemy1.health.toString()
            enemy2Health.text = enemy2.health.toString()
        }
    }

    fun createEnemies() {
        when (Random.nextInt(0, 4))
        {
            0 -> {
                imageViews[0].setImageResource(R.drawable.enemy_healer)
                enemy1 = Healer("Demon")
            }
            1 -> {
                imageViews[0].setImageResource(R.drawable.enemy_tank)
                enemy1 = Tank("Orc")
            }
            2 -> {
                imageViews[0].setImageResource(R.drawable.enemy_mage)
                enemy1 = Mage("Goblin")
            }
            else -> {
                imageViews[0].setImageResource(R.drawable.enemy_mage)
                enemy1 = Mage("Goblin")
            }
        }

        when (Random.nextInt(0, 4))
        {
            0 -> {
                imageViews[1].setImageResource(R.drawable.enemy_healer)
                enemy2 = Healer("Demon")
            }
            1 -> {
                imageViews[1].setImageResource(R.drawable.enemy_tank)
                enemy2 = Tank("Orc")
            }
            2 -> {
                imageViews[1].setImageResource(R.drawable.enemy_mage)
                enemy2 = Mage("Goblin")
            }
            else -> {
                imageViews[1].setImageResource(R.drawable.enemy_mage)
                enemy2 = Mage("Goblin")
            }
        }

        enemy1Health.text = enemy1.health.toString()
        enemy1Name.text = enemy1.name

        enemy2Health.text = enemy2.health.toString()
        enemy2Name.text = enemy2.name
    }

    fun createPlayer(archType: String, playerName: String) {
        if(archType == "mage") {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.mage)
            playerArch = Mage(playerName)
            characterHealth.text = playerArch.health.toString()
        }
        else if(archType == "tank") {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.tank)
            playerArch = Tank(playerName)
            characterHealth.text = playerArch.health.toString()
        }
        else {
            val player:ImageView = findViewById(R.id.player)
            player.setImageResource(R.drawable.healer)
            playerArch = Healer(playerName)
            characterHealth.text = playerArch.health.toString()
        }

        val textView: TextView = findViewById(R.id.charName) as TextView
        textView.text = playerName
    }

    fun runRound(userInput: String) {
        val e1Attack = Random.nextInt(0, 2)
        val e2Attack = Random.nextInt(0, 2)
        val attacksFirst = Random.nextInt(0, 2)
        // Player goes first
        if(attacksFirst == 0) {
            if(userInput == "heal") {
                playerArch.heal()
            }
            else if(userInput == "attack") {
                if (enemy1.health > 0) {
                    // Enemy blocks
                    if(e1Attack == 2) {
                        playerArch.attack(enemy1, enemy1.blockAmount)
                    }
                    else {
                        playerArch.attack(enemy1, 0)
                    }
                }
                else if (enemy2.health > 0) {
                    // Enemy blocks
                    if(e2Attack == 2) {
                        playerArch.attack(enemy2, enemy2.blockAmount)
                    }
                    else {
                        playerArch.attack(enemy2, 0)
                    }
                }
            }
        }
        // Enemy1 goes first
        else if(attacksFirst == 1) {
            // Attack
            if(e1Attack == 0) {
                if (userInput == "block") {
                    enemy1.attack(playerArch, playerArch.blockAmount)
                }
                else {
                    enemy1.attack(playerArch, 0)
                }
            }
            // Heal
            else if(e1Attack == 1) {
                enemy1.heal()
            }
        }
        else {
            // Attack
            if(e2Attack == 0) {
                if (userInput == "block") {
                    enemy2.attack(playerArch, playerArch.blockAmount)
                }
                else {
                    enemy2.attack(playerArch, 0)
                }
            }
            // Heal
            else if(e2Attack == 1) {
                enemy2.heal()
            }
        }
    }

}
