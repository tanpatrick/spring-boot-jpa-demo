package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.CreatePostDto
import github.tanpatrick.demo.dto.PostDto
import github.tanpatrick.demo.dto.UpdatePostDto
import github.tanpatrick.demo.service.PostService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val service: PostService
) {
    @PostMapping
    fun create(@RequestBody post: CreatePostDto) = service.create(post)

    @GetMapping
    fun findAll(): List<PostDto> = service.findAll()

    @GetMapping("/{postId}")
    fun findById(@PathVariable postId: Long): PostDto = service.findById(postId)

    @PostMapping("/{postId}")
    fun update(@PathVariable postId: Long, post: UpdatePostDto) = service.update(postId, post)
}