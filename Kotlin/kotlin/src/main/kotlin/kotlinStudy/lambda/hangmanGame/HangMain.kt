package kotlinStudy.lambda.hangmanGame

val possibleWordOfFruits = arrayOf("morango", "uva", "abacate", "melancia", "manga")
var remainingTries = 0
var letter: Char = '_'
val incorrectLetters = mutableListOf<Char>()
lateinit var correctLetters: MutableList<Char>

var lettersOfWordFruit = mutableListOf<Char>()

fun main(args: Array<String>) {


    val wordOfFruit = getWord()
    lettersOfWordFruit = toCharacterArray(wordOfFruit)

    remainingTries = lettersOfWordFruit.size

    correctLetters = CharArray(remainingTries) {
        '_'
    }.toMutableList()

    //Begin
    println(wordOfFruit)
    printHeader()

    do {

        game()
        if (verifyWinner()) {
            println("*** You Won, the word is: ${charToString(correctLetters)} ***")
            break
        }
        if (remainingTries == 0) {
            println("*** You Lost, try again ***")
        }

    } while (remainingTries > 0)

}

fun getWord(): String {

    return possibleWordOfFruits.random()

}

fun toCharacterArray(word: String): MutableList<Char> {

    val lettersOfAWord = mutableListOf<Char>()
    word.forEach {
        lettersOfAWord.add(it)
    }
    return lettersOfAWord

}

fun printHeader() {

    println("*************************")
    println("    * Hangman Game *")
    println("*************************")
}

fun game() {

    print("Type in the next letter -> ")
    letter = readLine()!![0]

    if (!isLetterOnTheWordGiven(letter)) {
        remainingTries--
        addLetterToIncorrectLetters(letter)
    } else {
        AddLetterToCorrectLetters(letter)
    }

    println("Remaining Tries: $remainingTries")
    println("Incorrect Letters: $incorrectLetters")
    showUserCorrectChosenWords()

}


fun isLetterOnTheWordGiven(letter: Char): Boolean {

    return lettersOfWordFruit.contains(letter)

}

fun addLetterToIncorrectLetters(letter: Char) {
    incorrectLetters.add(letter)
}

fun AddLetterToCorrectLetters(letter: Char) {

    lettersOfWordFruit.forEachIndexed { index, value ->
        if (letter == value) correctLetters.set(index, value)
    }
}

fun showUserCorrectChosenWords() {

    print("\nWord: ")
    correctLetters.forEach { item ->
        print(item)
    }
    print('\n')
}

fun verifyWinner(): Boolean {
    return correctLetters.equals(lettersOfWordFruit)

}

fun charToString(list: MutableList<Char>): String? {

    var result:String? = ""
    list.forEach { item ->
        result += item
    }
    return result
}

