package dao

import org.springframework.stereotype.Component
import entity.User
import java.util.*

/**
 * @author vinicius.moraes on 8/8/18
 */

@Component
class UserDAO {

    var usersCount: Int = 3

    val users: MutableList<User> = mutableListOf()

    init {
        users.add(User(1, "Adam", Date()))
        users.add(User(2, "Eve", Date()))
        users.add(User(3, "Jack", Date()))
    }

    fun findAll(): List<User> = users

    fun save(user: User) : User{

        if (user.id == 0) user.id = ++usersCount

        users.add(user)

        return user
    }

    fun findOne(id: Int) = users.find { u -> u.id == id }

    fun delete(id: Int): User? {

        val user = this.findOne(id)

        users.remove(user)

        return user

    }
}
