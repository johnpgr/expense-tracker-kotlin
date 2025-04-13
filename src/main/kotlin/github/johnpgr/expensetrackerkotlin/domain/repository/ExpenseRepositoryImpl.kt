package github.johnpgr.expensetrackerkotlin.domain.repository

import github.johnpgr.expensetrackerkotlin.domain.entity.Expense
import github.johnpgr.expensetrackerkotlin.domain.entity.expenseEntity
import kotlinx.coroutines.flow.Flow
import org.komapper.core.dsl.Meta
import org.komapper.core.dsl.QueryDsl
import org.komapper.core.dsl.query.firstOrNull
import org.komapper.r2dbc.R2dbcDatabase
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*

@Component
class ExpenseRepositoryImpl(private val database: R2dbcDatabase) : IExpenseRepository {
    private val expenseMeta = Meta.expenseEntity

    override suspend fun findById(id: UUID): Expense? {
        return database.runQuery {
            QueryDsl.from(expenseMeta).where { expenseMeta.id eq id }.firstOrNull()
        }
    }

    override fun findByUserId(userId: UUID): Flow<Expense> {
        return database.flowQuery {
            QueryDsl.from(expenseMeta).where { expenseMeta.userId eq userId }
        }
    }

    override fun findByCategoryId(categoryId: UUID): Flow<Expense> {
        return database.flowQuery {
            QueryDsl.from(expenseMeta).where { expenseMeta.categoryId eq categoryId }
        }
    }

    override fun findByUserIdAndDateBetween(
        userId: UUID,
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): Flow<Expense> {
        return database.flowQuery {
            QueryDsl.from(expenseMeta)
                .where {
                    expenseMeta.userId eq userId
                    and {
                        expenseMeta.date between startDate..endDate
                    }
                }
        }
    }

    override suspend fun create(expense: Expense): Expense {
        return database.runQuery {
            QueryDsl.insert(expenseMeta).single(expense)
        }
    }

    override suspend fun update(expense: Expense): Expense {
        return database.runQuery {
            QueryDsl.update(expenseMeta).single(expense)
        }
    }

    override suspend fun delete(id: UUID) {
        database.runQuery {
            QueryDsl.delete(expenseMeta).where { expenseMeta.id eq id }
        }
    }
}