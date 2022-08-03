package github.tanpatrick.demo.dto

import org.apache.commons.lang3.builder.EqualsBuilder
import java.time.LocalDateTime

data class CommentDto(
    val id: Long? = null,
    val body: String,
    val createdAt: LocalDateTime? = null
) {

    override fun equals(other: Any?): Boolean {
        if (other !is CommentDto) {
            return false
        }

        return EqualsBuilder()
            .append(id, other.id)
            .append(body, other.body)
            .isEquals
    }
}