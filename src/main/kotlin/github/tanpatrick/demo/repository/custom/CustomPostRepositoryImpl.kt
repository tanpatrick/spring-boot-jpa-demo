package github.tanpatrick.demo.repository.custom

import github.tanpatrick.demo.entity.PostEntity
import github.tanpatrick.demo.entity.PostEntity_
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.JoinType

class CustomPostRepositoryImpl : CustomPostRepository {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findAllFetchComments(): List<PostEntity> {
        val criteriaBuilder = entityManager.criteriaBuilder
        val criteriaQuery = criteriaBuilder.createQuery(PostEntity::class.java)

        val root = criteriaQuery.from(PostEntity::class.java)
        root.fetch(PostEntity_.comments, JoinType.LEFT)

        criteriaQuery.distinct(true)
        criteriaQuery.orderBy(
            criteriaBuilder.asc(root.get(PostEntity_.id))
        )

        val query = entityManager.createQuery(criteriaQuery)
        return query.resultList
    }
}