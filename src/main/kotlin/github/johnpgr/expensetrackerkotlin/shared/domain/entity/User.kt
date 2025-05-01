package github.johnpgr.expensetrackerkotlin.shared.domain.entity

import org.komapper.annotation.KomapperEntity
import org.komapper.annotation.KomapperId
import org.komapper.annotation.KomapperTable
import java.time.LocalDateTime
import java.util.UUID

@KomapperEntity
@KomapperTable("users")
data class User(
    @KomapperId
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val email: String,
    val passwordHash: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
