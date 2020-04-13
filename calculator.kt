import java.util.Scanner
import kotlin.math.pow


class Calculator() {
    private val intPattern = Regex("^[-]?[0-9]+$")
    private val floatPattern = Regex("^[-]?[0-9]+[.][0-9]*$")
    private val addPattern = Regex("^\\+$")
    private val subPattern = Regex("^-$")
    private val mulPattern = Regex("^\\*$")
    private val divPattern = Regex("^/$")
    private val expPattern = Regex("^\\^$")
    private val reader = Scanner(System.`in`)
    private var running = true

    private fun parseInput(input: String): Int {
        if (intPattern.containsMatchIn(input)) {
            return 0
        } else if (floatPattern.containsMatchIn(input)) {
            return 0
        } else if (input.toUpperCase().equals("QUIT")) {
            return 1
        } else if (addPattern.containsMatchIn(input)) {
            return 2
        } else if (subPattern.containsMatchIn(input)) {
            return 3
        } else if (mulPattern.containsMatchIn(input)) {
            return 4
        } else if (divPattern.containsMatchIn(input)) {
            return 5
        } else if (expPattern.containsMatchIn(input)) {
            return 6
        } else {
            return 7
        }
    }

    private fun getNumber(): Double {
        while (true) {
            print("Enter a number:\n")
            val userInput = reader.next()
            val inputCode = parseInput(userInput)
            if (inputCode == 0) {
                return userInput.toDouble()
            } else if (inputCode == 1) {
                running = false
                return -1.0
            } else {
                print("try again\n")
            }
        }
    }

    private fun getOperator(): Int {
        while (true) {
            print("Enter an operator:\n")
            val userInput = reader.next()
            val inputCode = parseInput(userInput)
            if (inputCode in 2..6) {
                return inputCode
            } else if (inputCode == 1) {
                running = false
                return -1
            } else {
                print("try again\n")
            }
        }
    }

    private fun performOperation(current: Double, operator: Int, number: Double): Double {
        when (operator) {
            2 -> return current + number
            3 -> return current - number
            4 -> return current * number
            5 -> return current / number
            6 -> return current.pow(number)
            else -> {
                println("Error! operator is not correct")
                return -1.0
            }
        }
    }

    fun run() {
        var current: Double
        var operator: Int
        var number: Double

        current = getNumber()
        while (running) {
            operator = getOperator()
            if (!running) break

            number = getNumber()
            if (!running) break

            while(number == 0.0 && operator == 5){
                println("Can't divide by zero. Try again")
                number = getNumber()
                if (!running) break
            }

            current = performOperation(current, operator, number)
            println(current)
        }
        println("Goodbye")
    }
}

fun main(args: Array<String>) {
    val c = Calculator()
    c.run()
}
