package domain.entity

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.validation.constraints.Past
import javax.validation.constraints.Size

/**
 * @author vinicius.moraes on 8/8/18
 */

@ApiModel(description = "All details about user")
@Entity
data class User(
        @Id
        @GeneratedValue
        var id: Int = 0,
        @Size(min=2)
        @ApiModelProperty(notes = "Name must have minimum of 2 characters")
        @Column(name= "name",nullable = false)
        val name: String = "",
        @Past
        @ApiModelProperty(notes = "Birthday date must be in the past")
        @Column(name="birthday",nullable = false)
        val birthday: Date = Date()
)