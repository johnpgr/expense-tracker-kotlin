package github.johnpgr.expensetrackerkotlin.config

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import io.r2dbc.spi.ConnectionFactory
import org.komapper.r2dbc.R2dbcDatabase
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseConfig {
    @Bean
    fun connectionFactory(
        @Value("\${spring.r2dbc.host}") host: String,
        @Value("\${spring.r2dbc.port}") port: Int,
        @Value("\${spring.r2dbc.database}") database: String,
        @Value("\${spring.r2dbc.username}") username: String,
        @Value("\${spring.r2dbc.password}") password: String
    ): ConnectionFactory {
        return PostgresqlConnectionFactory(
            PostgresqlConnectionConfiguration.builder()
                .host(host)
                .port(port)
                .database(database)
                .username(username)
                .password(password)
                .build()
        )
    }

    @Bean
    fun r2dbcDatabase(connectionFactory: ConnectionFactory): R2dbcDatabase {
        val dialect = org.komapper.dialect.postgresql.r2dbc.PostgreSqlR2dbcDialect()
        return R2dbcDatabase(connectionFactory, dialect)
    }
}