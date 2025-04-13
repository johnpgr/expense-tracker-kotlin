package github.johnpgr.expensetrackerkotlin.domain.repository

import github.johnpgr.expensetrackerkotlin.domain.entity.Category
import github.johnpgr.expensetrackerkotlin.domain.entity.categoryEntity
import kotlinx.coroutines.flow.Flow
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.firstOrNull
import org.komapper.r2dbc.R2dbcDatabase
import org.springframework.stereotype.Component
import java.util.*

@Component
class CategoryRepositoryImpl(private val database: R2dbcDatabase) : ICategoryRepository {
    private val categoryMeta = Meta.categoryEntity

    override suspend fun findById(id: UUID): Category? {
        return database.runQuery {
            QueryDsl.from(categoryMeta).where {
                categoryMeta.id eq id
            }.firstOrNull()
        }
    }

    override fun findByUserId(userId: UUID): Flow<Category> {
        return database.flowQuery {
            QueryDsl.from(categoryMeta).where {
                categoryMeta.userId eq userId
            }
        }
    }

    override suspend fun create(category: Category): Category {
        return database.runQuery {
            QueryDsl.insert(categoryMeta).single(category)
        }
    }

    override suspend fun update(category: Category): Category {
        return database.runQuery {
            QueryDsl.update(categoryMeta).single(category)
        }
    }

    override suspend fun delete(id: UUID) {
        database.runQuery {
            QueryDsl.delete(categoryMeta).where { categoryMeta.id eq id }
        }
    }
}