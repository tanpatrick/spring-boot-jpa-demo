package github.tanpatrick.demo.entity

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OrderBy
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime

@Entity
@Table(name = "posts")
class PostEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var body: String,

    @Enumerated(EnumType.STRING)
    var status: PostStatus = PostStatus.DRAFT
) {
    @OneToMany(
        cascade = [
            CascadeType.PERSIST,
            CascadeType.MERGE
        ],
        fetch = FetchType.LAZY,
        mappedBy = "post"
    )
    @OrderBy("id")
    val comments: MutableSet<CommentEntity> = mutableSetOf()

    @Column(nullable = false, updatable = false)
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()

    fun addComment(body: String, commentId: Long? = null) {
        comments.add(
            CommentEntity(
                id = commentId,
                body = body,
                post = this,
            )
        )
    }
}

enum class PostStatus {
    DRAFT,
    PUBLISHED
}