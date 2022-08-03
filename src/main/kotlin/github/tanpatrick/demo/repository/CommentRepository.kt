package github.tanpatrick.demo.repository

import github.tanpatrick.demo.entity.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface CommentRepository : JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.post.id = :postId")
    fun deleteAllByPostId(@Param("postId") postId: Long)

}