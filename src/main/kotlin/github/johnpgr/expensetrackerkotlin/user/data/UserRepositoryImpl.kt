package github.johnpgr.expensetrackerkotlin.domain.repository

import kotlinx.coroutines.flow.Flow
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.firstOrNull
import org.komapper.r2dbc.R2dbcDatabase
import org.springframework.stereotype.Component
import java.util.*
import github.johnpgr.expensetrackerkotlin.shared.data.UserRepository
import github.johnpgr.expensetrackerkotlin.shared.domain.entity.User

@Component
class UserRepositoryImpl(private val database: R2dbcDatabase) : UserRepository {
    private val userMeta = Meta.userEntity

    override suspend fun findById(id: UUID): User? {
        return database.runQuery {
            QueryDsl.from(userMeta).where { userMeta.id eq id }.firstOrNull()
        }
    }

    override suspend fun findByEmail(email: String): User? {
        return database.runQuery {
            QueryDsl.from(userMeta).where { userMeta.email eq email }.firstOrNull()
        }
    }

    override suspend fun create(user: User): User {
        return database.runQuery {
            QueryDsl.insert(userMeta).single(user)
        }
    }

    override suspend fun update(user: User): User {
        return database.runQuery {
            QueryDsl.update(userMeta).single(user)
        }
    }

    override suspend fun delete(id: UUID) {
        database.runQuery {
            QueryDsl.delete(userMeta).where { userMeta.id eq id }
        }
    }

    override fun findAll(): Flow<User> {
        return database.flowQuery {
            QueryDsl.from(userMeta)
        }
    }
}
