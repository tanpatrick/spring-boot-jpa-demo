package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.CreatePostDto
import github.tanpatrick.demo.dto.PostDto
import org.assertj.core.api.Assertions.assertThat
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
}
