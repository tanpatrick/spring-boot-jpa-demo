package github.tanpatrick.demo.repository

import github.tanpatrick.demo.entity.PostEntity
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import java.util.Optional

interface PostRepository : JpaRepository<PostEntity, Long> {

    @EntityGraph(attributePaths = [
        "comments"
    ])
    fun findPostCommentsById(postId: Long): Optional<PostEntity>
}