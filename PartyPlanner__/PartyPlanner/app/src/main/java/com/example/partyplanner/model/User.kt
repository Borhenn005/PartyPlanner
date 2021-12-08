package com.example.partyplanner.model

class User()
{
    internal var name: String? = null
    internal var email: String? = null
    internal var password: String? = null

    // Getter name
    fun getName(): String? {
        return name
    }
    // Setter name
    fun setName(newName: String?) {
        name = newName
    }

    // Getter email
    fun getEmail(): String? {
        return email
    }
    // Setter email
    fun setEmail(newEmail: String?) {
        email = newEmail
    }

    // Getter password
    fun getPassword(): String? {
        return password
    }
    // Setter password
    fun setPassword(newPassword: String?) {
        password = newPassword
    }


}


