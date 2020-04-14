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
    private var clear = false

    private fun parseInput(input: String): Int {
        if (input.toUpperCase().equals("QUIT")) {
            running = false
            return 0
        } else if (input.toUpperCase().equals("CLEAR")) {
            clear = true
            return 0
        } else if (intPattern.containsMatchIn(input)) {
            return 1
        } else if (floatPattern.containsMatchIn(input)) {
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
            return 8
        }
    }

    private fun getNumber(): Double {
        while (true) {
            print("Enter a number:\n")
            val userInput = reader.next()
            val inputCode = parseInput(userInput)
            if (inputCode == 1) {
                return userInput.toDouble()
            } else if (inputCode == 0) {
                return inputCode.toDouble()
            } else {
                print("try again\n")
            }
        }
    }

    private fun getOperator(): Char {
        while (true) {
            print("Enter an operator:\n")
            val operator = reader.next()
            val inputCode = parseInput(operator)
            if (inputCode in 2..6) {
                return operator[0]
            } else if (inputCode == 0) {
                return 'x'
            } else {
                print("try again\n")
            }
        }
    }

    private fun performOperation(current: Double, operator: Char, number: Double): Double {
        when (operator) {
            '+' -> return current + number
            '-' -> return current - number
            '*' -> return current * number
            '/' -> return current / number
            '^' -> return current.pow(number)
            else -> {
                println("Operator error")
                this.running = false
                return 0.0
            }
        }
    }

    fun reset() {
        clear = false
    }

    fun run(): Int {
        var current: Double
        var operator: Char
        var number: Double

        current = getNumber()
        if (!running) return 0
        if (clear) return 1
        while (true) {
            println(current)
            operator = getOperator()
            if (!running) return 0
            if (clear) return 1

            number = getNumber()
            if (!running) return 0
            if (clear) return 1

            while (number == 0.0 && operator == '/') {
                println("Can't divide by zero. Try again")
                number = getNumber()
                if (!running) return 0
                if (clear) return 1
            }
            val oldCurrent = current
            current = performOperation(current, operator, number)
            println("$oldCurrent $operator $number = $current")
        }
    }
}

fun main(args: Array<String>) {
    val c = Calculator()
    var returnCode: Int
    returnCode = c.run()
    while (returnCode != 0) {
        c.reset()
        returnCode = c.run()
    }
    println("Goodbye!")
}
