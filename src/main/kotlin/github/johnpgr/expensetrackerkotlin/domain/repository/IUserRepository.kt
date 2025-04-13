package github.johnpgr.expensetrackerkotlin.domain.repository

import github.johnpgr.expensetrackerkotlin.domain.entity.User
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface IUserRepository {
    suspend fun findById(id: UUID): User?
    suspend fun findByEmail(email: String): User?
    suspend fun create(user: User): User
    suspend fun update(user: User): User
    suspend fun delete(id: UUID)
    fun findAll(): Flow<User>
}