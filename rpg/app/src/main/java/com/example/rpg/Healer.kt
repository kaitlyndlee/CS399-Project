package com.example.rpg

class Healer(newName: String) : Architecture(newName) {
    var healAmount: Int = 4

    fun heal() {
        this.health += this.healAmount
    }
}