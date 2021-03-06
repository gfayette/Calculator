import java.util.Scanner
import kotlin.math.pow

/**
 * Basic calculator program. Continuously asks the user for input and keeps
 * a running tally of the calculation. The user may enter 'C' to clear the
 * current calculation or 'Q' to quit the application.
 */
class Calculator() {
    private val intPattern = Regex("^[-]?[0-9]+$")
    private val floatPattern = Regex("^[-]?[0-9]+[.][0-9]*$")
    private val opPattern = Regex("^[%\\*\\+-/\\^]$")
    private val reader = Scanner(System.`in`)
    private var running = true
    private var clear = false

    /**
     * Parses the raw user input. Returns an Int representing the type of
     * input and sets instance variables if the user selects the clear or
     * quit commands.
     *
     * @param input The raw user input as a String.
     * @return An integer representing the type of input
     */
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

    /**
     * Ask the user to input a number. Supports integers as well as floating
     * point numbers. Continually asks for input until a valid number is entered
     * or the user enters the clear or quit commands.
     *
     * @return A Double representing the the user's input
     */
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

    /**
     * Ask the user to input an operator. Supports addition, subtraction,
     * multiplication, division, exponentiation, and modulus. Continuously asks
     * for input until a valid operator is entered or the user enters the clear
     * or quit commands.
     *
     * @return A Char representing the operator.
     */
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

    /**
     * Performs an operation and returns the result.
     *
     * @param current The first number in the operation.
     * @param operator The operation which is performed.
     * @param number The second number in the operation.
     * @return The result of the operation as a Double.
     */
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


    /**
     * Runs the calculator program by continuously asking the user for numbers
     * and operators in alternating order. Performs operations and keeps a
     * running tally of the calculation. Checks for divide by zero errors and
     * exits if the user enters the clear or quit command.
     *
     * @return The value of clear at the time the input loop exits.
     */
    fun run(): Boolean {
        var current: Double
        var operator: Char
        var number: Double

        running = true
        clear = false

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
}

/**
 * Entry point for the program. Displays a welcome message and begins the
 * program. Repeatedly calls the run() function in Calculator until the
 * user enters the quit command.
 *
 * @param args A String Array containing any arguments passed to the program.
 */
fun main(args: Array<String>) {
    println("Welcome! Type 'C' to clear, 'Q' to quit")
    val c = Calculator()
    while (c.run()) { }
    println("Goodbye!")
}
