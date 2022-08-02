package github.tanpatrick.demo.controller

import github.tanpatrick.demo.dto.PostDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
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
}
