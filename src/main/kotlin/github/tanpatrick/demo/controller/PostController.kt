package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.CreatePostDto
import github.tanpatrick.demo.dto.PostDto
import github.tanpatrick.demo.dto.UpdatePostDto
import github.tanpatrick.demo.service.PostService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/posts")
class PostController(
    private val service: PostService
) {
    @PostMapping
    fun create(@RequestBody post: CreatePostDto) = service.create(post)

    @DeleteMapping("/{postId}")
    fun delete(@PathVariable postId: Long) = service.delete(postId)

    @GetMapping
    fun findAll(@RequestParam fetchCommentsMode: FetchCommentMode = FetchCommentMode.NONE) = when(fetchCommentsMode) {
        FetchCommentMode.FETCH_COMMENTS_BY_ENTITY_GRAPH -> service.findAllWithCommentsFetchedByEntityGraph()
        else -> service.findAll()
    }

    @GetMapping("/{postId}")
    fun findById(@PathVariable postId: Long, @RequestParam fetchCommentsMode: FetchCommentMode = FetchCommentMode.NONE): PostDto = when(fetchCommentsMode) {
        FetchCommentMode.FETCH_COMMENTS_BY_ENTITY_GRAPH -> service.findByIdWithCommentsFetchedByEntityGraph(postId)
        FetchCommentMode.FETCH_COMMENTS_BY_LAZY_LOADING -> service.findByIdWithCommentsLazyLoaded(postId)
        else -> service.findById(postId)
    }

    @PostMapping("/{postId}")
    fun update(@PathVariable postId: Long, post: UpdatePostDto) = service.update(postId, post)
}

enum class FetchCommentMode {
    NONE,
    FETCH_COMMENTS_BY_ENTITY_GRAPH,
    FETCH_COMMENTS_BY_LAZY_LOADING
}