package com.example.recyclerviewrom.model

import com.github.javafaker.Color
import com.github.javafaker.Faker
import java.util.*

typealias UsersListener = (users:List<User>) -> Unit

class UsersService {

    private var users = mutableListOf<User>()

    private val listeners = mutableSetOf<UsersListener>()

    init {
        val faker = Faker.instance()
        IMAGES.shuffle()
       users = (1..100).map {
            User(
                id = it.toLong(),
                name = faker.name().name(),
                company = faker.company().name(),
                photo = IMAGES[it % IMAGES.size]
            )
        }.toMutableList()
    }

    fun getUsers():List<User>{
        return users
    }

    fun deleteUser(user: User){
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1){
            users.removeAt(indexToDelete)
            notifyChanges()
        }
    }

    fun moveUser(user: User, moveBy:Int){
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 || newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()

    }

    fun addListener(listener: UsersListener){
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener){
        listeners.remove(listener)
    }

    private fun notifyChanges(){
        listeners.forEach{it.invoke(users)}
    }





    companion object {
        private val IMAGES = mutableListOf(
            "https://www.flaticon.com/ru/free-icon/train_1661916",
            "https://www.flaticon.com/ru/free-icon/pictures_3174986",
            "https://www.flaticon.com/ru/free-icon/pictures_1327941",
            "https://www.flaticon.com/ru/free-icon/camera_8853218",
            "https://www.flaticon.com/ru/free-icon/hand-sanitizer_8403014",
            "https://www.flaticon.com/ru/free-icon/washing-hands_8948934",
            "https://www.flaticon.com/ru/free-icon/washing-hands_8403062",
            "https://www.flaticon.com/ru/free-icon/towel_8357765",
            "https://www.flaticon.com/ru/free-icon/creambath_8962118",
            "https://www.flaticon.com/ru/free-icon/sun-cream_3739850",
        )
    }


}