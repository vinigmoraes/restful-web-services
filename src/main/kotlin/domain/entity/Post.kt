package domain.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

/**
 * @author vinicius.moraes on 2018-12-17
 */

@Entity
data class Post(
        @Id
        @GeneratedValue
        val id: Int = 0,
        val description: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @field:JsonIgnore
        val user: User = User()
) {

        override fun toString(): String {
                return "Post(id=$id, description='$description')"
        }
}
