package repository

import entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author vinicius.moraes on 2018-12-16
 */

@Repository
interface UserRepository : JpaRepository<User, Int>
