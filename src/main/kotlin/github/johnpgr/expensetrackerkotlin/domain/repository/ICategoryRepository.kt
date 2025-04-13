package github.johnpgr.expensetrackerkotlin.domain.repository

import github.johnpgr.expensetrackerkotlin.domain.entity.Category
import kotlinx.coroutines.flow.Flow
import java.util.UUID

interface ICategoryRepository {
    suspend fun findById(id: UUID): Category?
    fun findByUserId(userId: UUID): Flow<Category>
    suspend fun create(category: Category): Category
    suspend fun update(category: Category): Category
    suspend fun delete(id: UUID)
}