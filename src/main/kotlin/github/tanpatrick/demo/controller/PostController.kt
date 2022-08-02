package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.PostDto
import github.tanpatrick.demo.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val service: PostService
) {
    @GetMapping
    fun findAll(): List<PostDto> = service.findAll()

    @GetMapping("/{postId}")
    fun findById(@PathVariable postId: Long): PostDto = service.findById(postId)
}