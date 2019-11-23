package com.example.rpg

open class Architecture (newName: String) {
    open var health = 50
        get() = field
        set(value) { field = value }
    open var name = newName
        get() = field
        set(value) { field = value }
    open var team: String? = null
        get() = field
        set(value) { field = value }
    open var attackDamage: Int = 5
        get() = field
        set(value) { field = value }

    fun attack(enemy: Architecture){
        enemy.health -= this.attackDamage
    }
}