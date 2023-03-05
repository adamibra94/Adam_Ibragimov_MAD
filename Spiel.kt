fun main() {
    val randomNumber = generateSecretNumber()
    var guess: String
    var tries = 0

    println("Hi! Welcome to the Guessing Game. Please input a 4-digit number.")
    println("Generated number: $randomNumber")

    while (true) {
        tries++
        guess = getValidGuess()
        val (n, m) = checkGuess(randomNumber, guess)
        println("Output: $n:$m")
        if (guess == randomNumber) {
            break
        }
    }

    println("Congratulations! You guessed the number in $tries tries.")
}

fun generateSecretNumber(): String {
    val digits = ('0'..'9').shuffled()
    return digits.take(4).joinToString("")
}

fun getValidGuess(): String {
    while (true) {
        print("User Input: ")
        val guess = readLine()?.trim()
        if (guess != null && guess.length == 4 && guess.all { it.isDigit() }) {
            return guess
        }
        println("Invalid guess. Please enter a 4-digit number.")
    }
}

fun checkGuess(secretNumber: String, guess: String): Pair<Int, Int> {
    val correctDigits = guess.filter { it in secretNumber }
    val n = correctDigits.length
    val m = guess.zip(secretNumber).count { (a, b) -> a == b }
    return Pair(n - m, m)
}
