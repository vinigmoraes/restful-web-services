package domain.repository

import domain.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author vinicius.moraes on 2018-12-17
 */

@Repository
interface PostRepository : JpaRepository<Post, Int>