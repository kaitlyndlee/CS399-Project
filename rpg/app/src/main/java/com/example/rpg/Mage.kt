package com.example.rpg

class Mage(newName: String) : Architecture(newName){

    override var attackDamage = 10
    override var health = 40
    var healAmount = 5

    fun heal(enemy: Architecture){
        this.health += this.healAmount
    }
}