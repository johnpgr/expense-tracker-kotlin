package github.johnpgr.expensetrackerkotlin.application.service

import github.johnpgr.expensetrackerkotlin.domain.entity.Expense
import github.johnpgr.expensetrackerkotlin.domain.repository.ICategoryRepository
import github.johnpgr.expensetrackerkotlin.domain.repository.IExpenseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.*

@Service
class ExpenseService(
    private val expenseRepository: IExpenseRepository, private val categoryRepository: ICategoryRepository
) {
    suspend fun getExpenseById(id: UUID): Expense? {
        return expenseRepository.findById(id)
    }

    fun getExpensesByUser(userId: UUID): Flow<Expense> {
        return expenseRepository.findByUserId(userId)
    }

    fun getExpensesByCategory(categoryId: UUID): Flow<Expense> {
        return expenseRepository.findByCategoryId(categoryId)
    }

    fun getExpensesByPeriod(userId: UUID, startDate: LocalDateTime, endDate: LocalDateTime): Flow<Expense> {
        return expenseRepository.findByUserIdAndDateBetween(userId, startDate, endDate)
    }

    suspend fun createExpense(expense: Expense): Expense {
        // Validações adicionais podem ser feitas aqui
        return expenseRepository.create(expense)
    }

    suspend fun updateExpense(id: UUID, expense: Expense): Expense {
        if (expense.id != id) {
            throw IllegalArgumentException("ID na URL não corresponde ao ID no corpo da requisição")
        }

        expenseRepository.findById(id) ?: throw NoSuchElementException("Despesa com ID $id não encontrada")

        return expenseRepository.update(expense)
    }

    suspend fun deleteExpense(id: UUID) {
        expenseRepository.findById(id) ?: throw NoSuchElementException("Despesa com ID $id não encontrada")
        expenseRepository.delete(id)
    }

    suspend fun getMonthlyExpensesSummary(userId: UUID, yearMonth: YearMonth): Map<String, BigDecimal> {
        val firstDay = yearMonth.atDay(1).atStartOfDay()
        val lastDay = yearMonth.atEndOfMonth().plusDays(1).atStartOfDay()

        val expenses = expenseRepository.findByUserIdAndDateBetween(userId, firstDay, lastDay).toList()

        // Agrupamento por categoria
        val expensesByCategory = expenses.groupBy { it.categoryId }

        val result = mutableMapOf<String, BigDecimal>()
        result["total"] = expenses.fold(BigDecimal.ZERO) { acc, expense -> acc + expense.amount }

        // Buscar nomes das categorias e adicionar totais ao mapa de resultado
        expensesByCategory.forEach { (categoryId, categoryExpenses) ->
            val categoryName = categoryRepository.findById(categoryId)?.name ?: "Desconhecida"
            val total = categoryExpenses.fold(BigDecimal.ZERO) { acc, expense -> acc + expense.amount }
            result[categoryName] = total
        }

        return result
    }
}