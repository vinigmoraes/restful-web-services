package domain.controller

import domain.entity.Post
import domain.entity.User
import domain.repository.UserRepository
import exception.UserNotFindException
import org.springframework.hateoas.Resource
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.accepted
import org.springframework.http.ResponseEntity.created
import org.springframework.validation.Errors
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import springfox.documentation.annotations.ApiIgnore
import validator.UserRequestValidator
import java.net.URI
import javax.validation.Valid

/**
 * @author vinicius.moraes on 8/13/18
 */

@RestController()
class UserController(
        val userRepository: UserRepository
){

    @InitBinder
    protected fun initBinder(binder: WebDataBinder) {
        binder.validator = UserRequestValidator()
    }

    @GetMapping("/users")
    fun retrieveAllUsers(): List<User> {

        return userRepository.findAll()

    }

    @GetMapping("/users/{id}")
    fun findById(@PathVariable id: Int): Resource<User> {

        val user = userRepository.findById(id).get()

        val resource = Resource<User>(user)

        val allUsersLink = linkTo(methodOn(this.javaClass).retrieveAllUsers())

        resource.add(allUsersLink.withRel("all-users"))

        return resource

    }

    @PostMapping("/users")
    fun create(@Valid @RequestBody user: User, @ApiIgnore errors: Errors): ResponseEntity<String> {
        val userSaved = userRepository.save(user)

        when {

            errors.hasErrors() -> return ResponseEntity(
                    errors.fieldError!!.defaultMessage, HttpStatus.BAD_REQUEST)
        }

        val location = newUserLocation(userSaved.id)

        return created(location).build()

    }

    @DeleteMapping("/users/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<String> {

        userRepository.deleteById(id)

        return accepted().build()

    }

    @GetMapping("/users/{id}/posts")
    fun retrieveAllPosts(@PathVariable id: Int): List<Post> {

        val user = userRepository.findById(id)

        if (!user.isPresent) {

            throw UserNotFindException("$id")
        }

        return user.get().posts
    }


    @PostMapping("/users/{id}/posts")
    fun createPost(@PathVariable id: Int, @RequestBody post: Post): ResponseEntity<String> {

        val user = userRepository.findById(id)

        if (!user.isPresent) {

            throw UserNotFindException("$id")
        }

        val location = newUserLocation(user.get().id)

        return created(location).build()

    }

    fun newUserLocation(userId: Int): URI {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userId).toUri()
    }
}