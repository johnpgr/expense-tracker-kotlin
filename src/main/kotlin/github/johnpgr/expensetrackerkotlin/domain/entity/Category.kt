package github.johnpgr.expensetrackerkotlin.domain.entity

import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import java.time.LocalDateTime
import java.util.UUID

@KomapperEntity
@KomapperTable("categories")
data class Category(
    @KomapperId
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val description: String? = null,
    val userId: UUID,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)