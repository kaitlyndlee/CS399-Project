package com.example.rpg

class Tank(newName: String) : Architecture(newName) {
    override var health = 125
    override var maxHealth = 125
    override var attackDamage = 5
    override var blockAmount = 8
    override var healAmount = 2
}