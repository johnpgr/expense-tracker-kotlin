package github.johnpgr.expensetrackerkotlin.shared.domain.entity

import org.komapper.annotation.*
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

@KomapperEntity
@KomapperTable("expenses")
data class Expense(
    @KomapperId
    val id: UUID = UUID.randomUUID(),
    val amount: BigDecimal,
    val description: String,
    val date: LocalDateTime,
    val userId: UUID,
    val categoryId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
