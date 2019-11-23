package com.example.rpg

class Tank(newName: String) : Architecture(newName) {
    override var health = 125
    override var attackDamage = 5

    fun block(damage: Double) {
        this.health = (damage / 2).toInt()
    }

}