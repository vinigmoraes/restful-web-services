package controller

import dao.UserDAO
import exception.UserNotFindException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.validation.Errors
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import user.User
import validator.UserRequestValidator
import java.net.URI
import javax.validation.Valid

/**
 * @author vinicius.moraes on 8/13/18
 */

@RestController()
class UserController() {

    @Autowired
    lateinit var userDAO: UserDAO

    @InitBinder
    protected fun initBinder(binder: WebDataBinder) {
        binder.validator = UserRequestValidator()
    }

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> {

        return userDAO.findAll()

    }

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: Int): Resource<User> {

        val user = userDAO.findOne(id) ?: throw UserNotFindException("id - $id")

        val resource = Resource<User>(user)

        val allUsersLink = linkTo(methodOn(this.javaClass).retrieveAllUsers())

        resource.add(allUsersLink.withRel("all-users"))

        return resource

    }

    @PostMapping("/users")
    fun create(@Valid @RequestBody user: User, errors: Errors): ResponseEntity<String> {
        val userSaved = userDAO.save(user)

        when {

            errors.hasErrors() -> return ResponseEntity(
                    errors.fieldError!!.defaultMessage, HttpStatus.BAD_REQUEST)
        }

        val location = newUserLocation(userSaved.id)

        return created(location).build()

    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Int): User? {

        val userDeleted = userDAO.delete(id)

        return userDeleted ?: throw UserNotFindException("id - $id")

    }

    fun newUserLocation(userId: Int): URI {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId).toUri()
    }
}