package github.johnpgr.expensetrackerkotlin.shared.data

import github.johnpgr.expensetrackerkotlin.shared.domain.entity.User
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface UserRepository {
    suspend fun findById(id: UUID): User?
    suspend fun findByEmail(email: String): User?
    suspend fun create(user: User): User
    suspend fun update(user: User): User
    suspend fun delete(id: UUID)
    fun findAll(): Flow<User>
}
