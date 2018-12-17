package domain.entity

import javax.persistence.*

/**
 * @author vinicius.moraes on 2018-12-17
 */

@Entity
class Post(
        @Id
        @GeneratedValue
        private val id: Int,
        private val description: String,

        @ManyToOne(fetch = FetchType.LAZY)
        private val user: User
)