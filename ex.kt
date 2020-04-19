import java.util.Scanner
import kotlin.math.pow

class ex() {


    private val intPattern = Regex("^[-]?[0-9]+$")
    private val floatPattern = Regex("^[-]?[0-9]+[.][0-9]*$")
    private val opPattern = Regex("^[%\\*\\+-/\\^]$")
    private val reader = Scanner(System.`in`)
    private var running = true
    private var clear = false

    private fun parseInput(input: String): Int {
        if (input.toUpperCase().equals("Q")) {
            running = false
            return 0
        } else if (input.toUpperCase().equals("C")) {
            running = false
            clear = true
            return 0
        } else if (opPattern.containsMatchIn(input)) {
            return 1
        } else if (intPattern.containsMatchIn(input)) {
            return 2
        } else if (floatPattern.containsMatchIn(input)) {
            return 2
        } else {
            return -1
        }
    }

    private fun getNumber(): Double {
        while (true) {
            print("Enter a number:\n")
            val userInput = reader.next()
            val inputCode = parseInput(userInput)
            if (inputCode == 2) {
                return userInput.toDouble()
            } else if (inputCode == 0) {
                return 0.0
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
            if (inputCode in 0..1) {
                return operator[0]
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
            '%' -> return current % number
            else -> {
                println("Operator error")
                this.running = false
                return 0.0
            }
        }
    }

    fun reset() {
        running = true
        clear = false
    }

    fun run(): Boolean {
        var current: Double
        var operator: Char
        var number: Double

        current = getNumber()
        if (!running) return clear
        while (true) {
            println(current)
            operator = getOperator()
            if (!running) return clear

            number = getNumber()
            if (!running) return clear

            while (number == 0.0 && operator == '/') {
                println("Can't divide by zero. Try again")
                number = getNumber()
                if (!running) return clear
            }
            val oldCurrent = current
            current = performOperation(current, operator, number)
            println("$oldCurrent $operator $number = $current")
        }
    }

    fun xx(a: Long) {

    }
}


fun main(args: Array<String>) {
    val c = ex()

    var a: Long = 10
    var b: Int = 10
    c.xx(a)




    println("Welcome! Type 'C' to clear, 'Q' to quit")

    while (c.run()) {
        c.reset()
    }
    println("Goodbye!")
}
