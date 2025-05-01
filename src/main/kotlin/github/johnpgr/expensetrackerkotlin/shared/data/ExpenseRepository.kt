package github.johnpgr.expensetrackerkotlin.shared.data

import github.johnpgr.expensetrackerkotlin.shared.domain.entity.Expense
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.util.UUID

interface ExpenseRepository {
    suspend fun findById(id: UUID): Expense?
    fun findByUserId(userId: UUID): Flow<Expense>
    fun findByCategoryId(categoryId: UUID): Flow<Expense>
    fun findByUserIdAndDateBetween(userId: UUID, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Expense>
    suspend fun create(expense: Expense): Expense
    suspend fun update(expense: Expense): Expense
    suspend fun delete(id: UUID)
}
