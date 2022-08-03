package github.tanpatrick.demo.repository.custom

import github.tanpatrick.demo.entity.PostEntity

interface CustomPostRepository {

    /**
     * Retrieve all posts and fetch comments
     */
    fun findAllFetchComments(): List<PostEntity>
}