package github.tanpatrick.demo.repository

import github.tanpatrick.demo.entity.PostEntity
import github.tanpatrick.demo.repository.custom.CustomPostRepository
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : CustomPostRepository, JpaRepository<PostEntity, Long> {

    @EntityGraph(
        attributePaths = [
            "comments"
        ]
    )
    fun findPostCommentsById(postId: Long): Optional<PostEntity>
}