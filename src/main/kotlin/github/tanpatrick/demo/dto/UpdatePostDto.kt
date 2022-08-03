package github.tanpatrick.demo.dto

data class UpdatePostDto(
    val title: String,
    val body: String,
    val comments: List<CommentDto> = emptyList()
)