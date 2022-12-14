package github.tanpatrick.demo.dto

data class CreatePostDto(
    val title: String,
    val body: String,
    val comments: List<CreateCommentDto> = emptyList()
)