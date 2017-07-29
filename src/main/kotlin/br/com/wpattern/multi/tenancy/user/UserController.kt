package br.com.wpattern.multi.tenancy.user

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(
        path = arrayOf("/user"),
        consumes = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE),
        produces = arrayOf(MediaType.APPLICATION_JSON_UTF8_VALUE)
)
class UserController @Autowired constructor(val userRepository: UserRepository) {

    @GetMapping(consumes = arrayOf(MediaType.ALL_VALUE))
    fun findAll(): ResponseEntity<List<User>> =
            ResponseEntity(userRepository.findAll(), HttpStatus.OK)

    @GetMapping(path = arrayOf("/{id}"), consumes = arrayOf(MediaType.ALL_VALUE))
    fun findById(@PathVariable(value = "id") id: Long): ResponseEntity<User> =
            userRepository.findById(id)
                    .map { ResponseEntity<User>(it, HttpStatus.OK) }
                    .orElse(ResponseEntity(HttpStatus.NOT_FOUND))

    @PostMapping
    fun add(@RequestBody user: User): ResponseEntity<User> =
            ResponseEntity(userRepository.save(user), HttpStatus.CREATED)

    @PutMapping
    fun update(@RequestBody user: User): ResponseEntity<User> =
            ResponseEntity(userRepository.save(user), HttpStatus.OK)

    @DeleteMapping
    fun delete(@RequestBody user: User?) =
            userRepository.delete(user)

}
