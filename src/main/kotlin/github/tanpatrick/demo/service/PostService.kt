package github.tanpatrick.demo.service

import github.tanpatrick.demo.dto.CreatePostDto
import github.tanpatrick.demo.dto.PostDto
import github.tanpatrick.demo.dto.UpdatePostDto
import github.tanpatrick.demo.entity.PostEntity
import github.tanpatrick.demo.exception.RecordNotFound
import github.tanpatrick.demo.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository
) {
    fun create(post: CreatePostDto): PostDto {
        val entity = repository.save(PostEntity(
            title =  post.title,
            body = post.body
        ))
        return convertToDto(entity)
    }
    fun findAll(): List<PostDto> {
        return repository.findAll()
            .map(this::convertToDto)
    }

    fun findById(postId: Long): PostDto {
        val entity = findEntityById(postId)
        return convertToDto(entity)
    }

    fun update(postId: Long, post: UpdatePostDto): PostDto {
        val entity = PostEntity(
            id = postId,
            title = post.title,
            body = post.body
        )

        return convertToDto(repository.save(entity))
    }

    private fun findEntityById(postId: Long) = repository.findById(postId)
        .orElseThrow { RecordNotFound(postId) }

    private fun convertToDto(entity: PostEntity) = PostDto(
        id = entity.id ?: 0L,
        title = entity.title,
        body = entity.body,
        createdAt = entity.createdAt
    )
}