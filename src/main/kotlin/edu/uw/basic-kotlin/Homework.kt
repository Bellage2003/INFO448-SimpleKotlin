package edu.uw.basickotlin
import kotlin.math.round
import kotlin.math.roundToInt

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}
fun main() {
    // Sample Testing for Money Class
    val money1 = Money(5, "GBP")
    val money2 = Money(15, "EUR")

    val result = money1 + money2
    println(result)
}


// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(input: Any): String {
    return when (input) {
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Double -> "I don't understand"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}

// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, operation: (Int, Int) -> Int): Int {
    return operation(a, b)
}

// write a class "Person" with first name, last name and age
class Person(val firstName: String, val lastName: String, val age: Int) {
    val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}

// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money(var amount: Int, val currency: String) {

    init {
        if (amount < 0) {
            throw IllegalArgumentException("Amount cannot be negative")
        }
        val validCurrencies = setOf("USD", "EUR", "CAN", "GBP")
        if (currency !in validCurrencies) {
            throw IllegalArgumentException("Invalid currency")
        }
    }

    fun convert(targetCurrency: String): Money {
        val conversionRates = mapOf(
            "USD" to mapOf("GBP" to 0.5, "EUR" to 1.5, "CAN" to 1.25, "USD" to 1.0),
            "GBP" to mapOf("USD" to 2.0, "EUR" to 3.0, "CAN" to 2.5, "GBP" to 1.0),
            "EUR" to mapOf("USD" to 0.67, "GBP" to 0.33, "CAN" to 0.83, "EUR" to 1.0),
            "CAN" to mapOf("USD" to 0.8, "GBP" to 0.4, "EUR" to 1.2, "CAN" to 1.0)
        )

        val conversionRate = conversionRates[currency]?.get(targetCurrency)
        ?: throw IllegalArgumentException("Invalid currency conversion")

        val convertedAmount = amount * conversionRate
        return Money(convertedAmount.roundToInt(), targetCurrency)
    }

    operator fun plus(other: Money): Money {
        val convertedOther = other.convert(currency)
        return Money(amount + convertedOther.amount, currency)
    }

    override fun toString(): String {
        return "$amount $currency"
    }
}
