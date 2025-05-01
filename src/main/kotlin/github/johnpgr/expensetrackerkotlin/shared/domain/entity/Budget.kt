package github.johnpgr.expensetrackerkotlin.shared.domain.entity

import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.YearMonth
import java.util.UUID

@KomapperEntity
@KomapperTable("budgets")
data class Budget(
    @KomapperId
    val id: UUID = UUID.randomUUID(),
    val amount: BigDecimal,
    val yearMonth: YearMonth,
    val userId: UUID,
    val categoryId: UUID? = null, // null para orçamento geral
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
