package github.tanpatrick.demo.dto

import org.apache.commons.lang3.builder.EqualsBuilder
import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val title: String,
    val body: String,
    val createdAt: LocalDateTime? = null
) {

    override fun equals(other: Any?): Boolean {
        if (other !is PostDto) {
            return false
        }

        return EqualsBuilder()
            .append(id, other.id)
            .append(title, other.title)
            .append(body, other.body)
            .isEquals
    }
}