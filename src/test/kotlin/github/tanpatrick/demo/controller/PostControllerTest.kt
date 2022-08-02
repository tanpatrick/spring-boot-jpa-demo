package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.CommentDto
import github.tanpatrick.demo.dto.CreateCommentDto
import github.tanpatrick.demo.dto.CreatePostDto
import github.tanpatrick.demo.dto.PostDto
import github.tanpatrick.demo.dto.UpdatePostDto
import org.assertj.core.api.Assertions.assertThat
import org.hibernate.LazyInitializationException
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.annotation.DirtiesContext.ClassMode

@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class PostControllerTest {

    @Autowired
    lateinit var controller: PostController

    @Test
    fun `Verify find all posts`() {
        assertThat(controller.findAll())
            .containsExactly(
                PostDto(
                    id = 1,
                    title = "Sunt aut facere repellat provident",
                    body = "Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut"
                ),
                PostDto(
                    id = 2,
                    title = "Qui est esse",
                    body = "Dolorem eum magni eos aperiam quia"
                )
            )
    }

    @Test
    fun `Verify find post by id`() {
        assertThat(controller.findById(1))
            .isEqualTo(
                PostDto(
                    id = 1,
                    title = "Sunt aut facere repellat provident",
                    body = "Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut"
                )
            )
    }

    @Test
    fun `Verify find post by id with comments fetched by EntityGraph`() {
        assertThat(controller.findById(1, FetchCommentMode.FETCH_COMMENTS_BY_ENTITY_GRAPH))
            .isEqualTo(
                PostDto(
                    id = 1,
                    title = "Sunt aut facere repellat provident",
                    body = "Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut",
                    comments = listOf(
                        CommentDto(
                            id = 1,
                            body = "Laudantium enim quasi est quidem magnam voluptate ipsam eos"
                        ),
                        CommentDto(
                            id = 2,
                            body = "Est natus enim nihil est dolore omnis voluptatem numquam"
                        )
                    )
                )
            )
    }

    @Test
    fun `Verify find post by id with lazy comments and should throw LazyInitializationException`() {
        assertThrows(LazyInitializationException::class.java, {
            controller.findById(1, fetchCommentsMode = FetchCommentMode.FETCH_COMMENTS_BY_LAZY_LOADING)
        }, "LazyInitializationException was expected")
    }

    @Test
    fun `Verify create post`() {
        val post = CreatePostDto(
            title = "Magnam facilis autem",
            body = "Dignissimos aperiam dolorem qui eum facilis quibusdam animi sint suscipit qui sint possimus"
        )
        val createdPost = PostDto(id = 3, title = post.title, body = post.body)

        assertThat(controller.create(post))
            .isEqualTo(createdPost)

        assertThat(controller.findAll())
            .containsExactly(
                PostDto(
                    id = 1,
                    title = "Sunt aut facere repellat provident",
                    body = "Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut"
                ),
                PostDto(
                    id = 2,
                    title = "Qui est esse",
                    body = "Dolorem eum magni eos aperiam quia"
                ),
                createdPost
            )
    }

    @Test
    fun `Verify create post with comments`() {
        val post = CreatePostDto(
            title = "Magnam facilis autem",
            body = "Dignissimos aperiam dolorem qui eum facilis quibusdam animi sint suscipit qui sint possimus",
            comments = listOf(
                CreateCommentDto("Vero eaque aliquid doloribus et culpa"),
                CreateCommentDto("Et fugit eligendi deleniti quidem qui sint nihil autem")
            )
        )
        val createdPost = PostDto(id = 3, title = post.title, body = post.body)

        assertThat(controller.create(post))
            .isEqualTo(createdPost)

        assertThat(controller.findById(3, FetchCommentMode.FETCH_COMMENTS_BY_ENTITY_GRAPH))
            .isEqualTo(
                PostDto(
                    id = 3,
                    title = post.title,
                    body = post.body,
                    comments = listOf(
                        CommentDto(id = 3, body = "Vero eaque aliquid doloribus et culpa"),
                        CommentDto(id = 4, body = "Et fugit eligendi deleniti quidem qui sint nihil autem")
                    )
                )
            )
    }

    @Test
    fun `Verify update post`() {
        val post = UpdatePostDto(
            title = "Magnam facilis autem",
            body = "Dignissimos aperiam dolorem qui eum facilis quibusdam animi sint suscipit qui sint possimus"
        )
        val updatedPost = PostDto(
            id = 2,
            title = post.title,
            body = post.body
        )

        assertThat(controller.update(2, post))
            .isEqualTo(updatedPost)

        assertThat(controller.findAll())
            .containsExactly(
                PostDto(
                    id = 1,
                    title = "Sunt aut facere repellat provident",
                    body = "Quia et suscipit suscipit recusandae consequuntur expedita et cum reprehenderit molestiae ut"
                ),
                updatedPost
            )
    }
}
