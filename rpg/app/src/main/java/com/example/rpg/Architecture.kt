package com.example.rpg

open class Architecture (newName: String) {
    open var health = 50
        get() = field
        set(value) {
            if(value <= 0) {
                field = 0
            }
            else {
                field = value
            }
        }
    open var name = newName
        get() = field
        set(value) { field = value }
    open var team: String? = null
        get() = field
        set(value) { field = value }
    open var attackDamage: Int = 5
        get() = field
        set(value) { field = value }
    open var  healAmount: Int = 0
        get() = field
        set(value) { field = value }
    open var  blockAmount: Int = 0
        get() = field
        set(value) { field = value }

    fun attack(enemy: Architecture, blockedDamage: Int) {
        enemy.health -= Math.abs((this.attackDamage - blockedDamage))
        if(enemy.health <= 0) {
            enemy.health = 0;
        }
    }

    fun heal() {
        this.health += this.healAmount
    }

    fun block(damage: Int) {
        this.health -= Math.abs((damage - blockAmount))
    }
}