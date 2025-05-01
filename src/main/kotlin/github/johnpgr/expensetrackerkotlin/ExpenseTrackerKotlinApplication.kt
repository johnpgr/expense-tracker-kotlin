package github.johnpgr.expensetrackerkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ExpenseTrackerKotlinApplication

fun main(args: Array<String>) {
    runApplication<ExpenseTrackerKotlinApplication>(*args)
}
