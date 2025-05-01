package github.johnpgr.expensetrackerkotlin.shared.data

import kotlinx.coroutines.flow.Flow
import java.util.UUID
import github.johnpgr.expensetrackerkotlin.shared.domain.entity.Category

interface CategoryRepository {
    suspend fun findById(id: UUID): Category?
    fun findByUserId(userId: UUID): Flow<Category>
    suspend fun create(category: Category): Category
    suspend fun update(category: Category): Category
    suspend fun delete(id: UUID)
}
