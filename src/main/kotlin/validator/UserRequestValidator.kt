package validator

import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator
import entity.User
import java.util.*

/**
 * @author vinicius.moraes on 9/9/18
 */
class UserRequestValidator : Validator {

    override fun validate(target: Any?, errors: Errors) {

        val user = target as User


        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
                "required.name", "Field id is required")

        if (user.birthday!!.after(Date())) {
            errors.rejectValue("birthday",
                    "invalid.date", "Field date need to be in the past")
        }
    }

    override fun supports(clazz: Class<*>): Boolean {
        return User().javaClass == clazz
    }
}