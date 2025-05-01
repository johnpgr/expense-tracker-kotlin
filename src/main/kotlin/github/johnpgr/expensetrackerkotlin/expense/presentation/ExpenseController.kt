package github.johnpgr.expensetrackerkotlin.presentation.controller

import github.johnpgr.expensetrackerkotlin.expense.domain.ExpenseService
import github.johnpgr.expensetrackerkotlin.shared.domain.entity.Expense
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID
import java.time.YearMonth

@RestController
@RequestMapping("/api/expenses")
class ExpenseController(private val expenseService: ExpenseService) {

    @GetMapping("/{id}")
    suspend fun getById(@PathVariable id: UUID): Expense {
        return expenseService.getExpenseById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/user/{userId}")
    fun getByUser(@PathVariable userId: UUID) = expenseService.getExpensesByUser(userId)

    @GetMapping("/category/{categoryId}")
    fun getByCategory(@PathVariable categoryId: UUID) = expenseService.getExpensesByCategory(categoryId)

    @GetMapping("/user/{userId}/period")
    fun getByPeriod(
        @PathVariable userId: UUID,
        @RequestParam startDate: LocalDateTime,
        @RequestParam endDate: LocalDateTime,
    ) = expenseService.getExpensesByPeriod(userId, startDate, endDate)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    suspend fun create(@RequestBody expense: Expense): Expense {
        return expenseService.createExpense(expense)
    }

    @PutMapping("/{id}")
    suspend fun update(
        @PathVariable id: UUID,
        @RequestBody expense: Expense
    ): Expense {
        return expenseService.updateExpense(id, expense)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    suspend fun delete(@PathVariable id: UUID) {
        expenseService.deleteExpense(id)
    }

    @GetMapping("/user/{userId}/summary/{year}/{month}")
    suspend fun getMonthlySummary(
        @PathVariable userId: UUID,
        @PathVariable year: Int,
        @PathVariable month: Int
    ): Map<String, BigDecimal> {
        return expenseService.getMonthlyExpensesSummary(userId, YearMonth.of(year, month))
    }
}
