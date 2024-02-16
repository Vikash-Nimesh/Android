package com.example.contactmanagerapp.room

class UserRepository(private val userDAO: UserDAO) {

    val users = userDAO.getAllUsers()

    suspend fun insert(user:User):Long{
        return userDAO.insertUser(user)
    }
    suspend fun delete(user: User){
        return userDAO.deleteUser(user)
    }
    suspend fun update(user: User){
        return userDAO.updateUser(user)
    }
    suspend fun deleteAll(){
        return userDAO.deleteAll()
    }

}