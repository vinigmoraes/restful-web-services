package user

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import java.util.*
import javax.validation.constraints.Past
import javax.validation.constraints.Size

/**
 * @author vinicius.moraes on 8/8/18
 */

@ApiModel(description = "All details about user")
data class User(
        var id: Int = 0,
        @Size(min=2)
        @ApiModelProperty(notes = "Name must have minimum of 2 characters")
        val name: String = "",
        @Past
        @ApiModelProperty(notes = "Birthday date must be in the past")
        val birthday: Date? = Date()
)