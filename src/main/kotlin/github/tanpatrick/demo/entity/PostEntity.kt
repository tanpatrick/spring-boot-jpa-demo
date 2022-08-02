package github.tanpatrick.demo.entity

import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.OrderBy
import javax.persistence.Table

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
) {
    @OneToMany(
        cascade = [CascadeType.PERSIST],
        fetch = FetchType.LAZY,
        mappedBy = "post"
    )
    @OrderBy("id")
    val comments: MutableSet<CommentEntity> = mutableSetOf()

    @Column(nullable = false, updatable = false)
    @CreatedDate
    val createdAt: LocalDateTime = LocalDateTime.now()

    fun addComment(body: String) {
        comments.add(
            CommentEntity(
                body = body,
                post = this
            )
        )
    }
}