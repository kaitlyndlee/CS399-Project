package com.example.rpg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        val e1Attack = Random.nextInt(0, 3)
        val e2Attack = Random.nextInt(0, 3)
        var attacksFirst = Random.nextInt(0, 3)
        var count = 0
        val ouputArray = arrayOf<TextView>(
            findViewById(R.id.output),
            findViewById(R.id.output2),
            findViewById(R.id.output3)
        )

        while(count < 3) {
            Log.d("TAG", "${attacksFirst}")
            // Player goes first
            if (attacksFirst == 0) {
                if (userInput == "heal") {
                    playerArch.heal()

                    ouputArray[count].text = getString(R.string.player_heal_toast, playerArch.healAmount)
//                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                    Log.d("TAG", "PLAYER HEAL")
                }
                else if (userInput == "attack") {
                    if (enemy1.health > 0) {
                        // Enemy blocks
                        if (e1Attack == 2) {
                            val enemyCurrentHealth = enemy1.health

                            playerArch.attack(enemy1, enemy1.blockAmount)

                            ouputArray[count].text = getString(R.string.player_attack_toast, enemy1.name,
                                    enemyCurrentHealth - enemy1.health)
                            Log.d("TAG", "PLAYER ATTACKS ENEMY 1 BLOCKS")
//                            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                        } else {
                            val enemyCurrentHealth = enemy1.health

                            playerArch.attack(enemy1, 0)

                            ouputArray[count].text = getString(R.string.player_attack_toast, enemy1.name,
                                    enemyCurrentHealth - enemy1.health)
//                            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                            Log.d("TAG", "PLAYER ATTACKS")
                        }
                    } else if (enemy2.health > 0) {
                        // Enemy blocks
                        if (e2Attack == 2) {
                            val enemyCurrentHealth = enemy2.health

                            playerArch.attack(enemy2, enemy2.blockAmount)

                            ouputArray[count].text = getString(R.string.player_attack_toast, enemy2.name,
                                    enemyCurrentHealth - enemy2.health)
//                            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                            Log.d("TAG", "PLAYER ATTACKS ENEMY 2 BLOCKS")
                        } else {
                            val enemyCurrentHealth = enemy2.health

                            playerArch.attack(enemy2, 0)

                            ouputArray[count].text = getString(R.string.player_attack_toast, enemy2.name,
                                    enemyCurrentHealth - enemy2.health)
//                            Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                            Log.d("TAG", "PLAYER ATTACKS ENEMY 2")
                        }
                    }
                }
                else if (userInput == "block") {
                    ouputArray[count].text = getString(R.string.player_block_toast)
                    Log.d("TAG", "PLAYER BLOCKS")
                }
            }
            // Enemy1 goes first
            else if (attacksFirst == 1) {
                // Attack
                if (e1Attack == 0) {
                    if (userInput == "block") {
                        val playerCurrentHealth = playerArch.health

                        enemy1.attack(playerArch, playerArch.blockAmount)

                        ouputArray[count].text = getString(R.string.enemy_attack_toast, enemy1.name,
                            playerCurrentHealth - playerArch.health)
//                        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                        Log.d("TAG", "ENEMY1 ATTACKS, PLAYER BLOCKS")
                    }
                    else {
                        val playerCurrentHealth = playerArch.health

                        enemy1.attack(playerArch, 0)

                        ouputArray[count].text = getString(R.string.enemy_attack_toast, enemy1.name,
                            playerCurrentHealth - playerArch.health)
//                        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                        Log.d("TAG", "ENEMY1 ATTACKS")
                    }
                }
                // Heal
                else if (e1Attack == 1) {
                    enemy1.heal()

                    ouputArray[count].text = getString(R.string.enemy_heal_toast, enemy2.name, enemy1.healAmount)
//                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                    Log.d("TAG", "ENEMY1 HEALS")
                }
                else if (e1Attack == 2) {
                    ouputArray[count].text = getString(R.string.enemy_block_toast, enemy1.name)
                    Log.d("TAG", "ENEMY1 ATTACKS, PLAYER BLOCKS")
                }
            }
            else if (attacksFirst == 2) {
                // Attack
                if (e2Attack == 0) {
                    if (userInput == "block") {
                        val playerCurrentHealth = playerArch.health

                        enemy2.attack(playerArch, playerArch.blockAmount)

                        ouputArray[count].text = getString(R.string.enemy_attack_toast, enemy2.name,
                            playerCurrentHealth - playerArch.health)
//                        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                        Log.d("TAG", "ENEMY2 ATTACKS, PLAYER BLOCKS")
                    } else {
                        val playerCurrentHealth = playerArch.health

                        enemy2.attack(playerArch, 0)

                        ouputArray[count].text = getString(R.string.enemy_attack_toast, enemy2.name,
                            playerCurrentHealth - playerArch.health)
//                        Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                        Log.d("TAG", "ENEMY2 ATTACKS")
                    }
                }
                // Heal
                else if (e2Attack == 1) {
                    enemy2.heal()

                    ouputArray[count].text = getString(R.string.enemy_heal_toast, enemy2.name, enemy2.healAmount)
//                    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
                    Log.d("TAG", "ENEMY2 HEALS")
                }
                else if (e1Attack == 2) {
                    ouputArray[count].text = getString(R.string.enemy_block_toast, enemy2.name)
                    Log.d("TAG", "ENEMY2 ATTACKS, PLAYER BLOCKS")
                }
            }
            count++

            attacksFirst = (attacksFirst + 1) % 3
        }
        Log.d("TAG", "----------------------")
    }

}

/*
button.setOnClickListener{
    val name = inputName.text.toString()
    val message = inputMessage.text.toString()
    val toastMessage = getString(R.string.toast_message, name, message)
    Toast.makeText(this, toastMessage, Toast.LENGTH_LONG).show()
}
*/