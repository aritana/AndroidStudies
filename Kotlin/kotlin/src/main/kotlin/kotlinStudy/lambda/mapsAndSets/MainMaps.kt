val bobData = mutableMapOf<String, String>(
    "name" to "Bob",
    "profession" to "CardPlayer",
    "country" to "USA"
)


fun main(args: Array<String>) {


    /**Keys and Values
    Keys are unique. The same key can't appear twice in a map,
    but different keys may point to the same value.
    All the keys have to be of the same type, and all values have to
    be of the same type.**/


    //accessingValues()
    //modifyingMutableMaps()
    // modifyingMutableMaps()
    //removingPairs()
    //interating()
    // runningTimeForMapOperations()
    //sets()
    //mapsAndFlatMap1()
   // mapsAndFlatMap2()

    modifyingMutableMaps()

}

fun accessingValues() {

    //returns an immutable of fixed size.
    var yearOfBirth = mapOf("Anna" to 1990, "Amanda" to 2000, "Junior" to 2021)
    //  println("Anna nasceu em ${yearOfBirth["Anna"]}")
    //  println("Joao nasceu em ${yearOfBirth["Joao"]}") //null

    //get
    //println("Repito: A Anna nasceu em ${yearOfBirth.get("Anna")}")

    //Mutable
    var namesAndScores = mutableMapOf<String, Int>("Anna" to 2, "Junior" to 5)

    //Empty
    var namesByScores = mutableMapOf<String, Int>()
}

fun modifyingMutableMaps() {

    bobData.put("state", "CA")
    bobData["city"] = "San Franscico"
    //println(bobData.put("name","Bobby")) //update or creates
    bobData["profesion"] = "Mailman" //update or creates

    val pair = "nickname" to "Bobby D"
    bobData += pair

    println(pair)
}

fun removingPairs() {
    println(bobData.remove("city"))//remove city and his value
    println(bobData.remove("state", "CA")) //remove state only if CA matches
}

fun interating() {

    var namesAndScores = mutableMapOf<String, Int>("Anna" to 2, "Junior" to 5)

    for ((player, score) in namesAndScores) {
        println("$player - $score")
    }

    //just keys
    for (player in namesAndScores.keys) {
        print("$player, ")
    }
    println()
}

fun runningTimeForMapOperations() {

    println("aritana".hashCode())
    //Use HashMap<K, V> for performance critical code.

}

fun sets() {
    /*
        Unordered collection of unique values of the same type.
        The order is not important.
     */

    //creating sets
    val names = setOf("Anna", "Brian")
    val hashSet = HashSet<Int>()

    //creating from arrays

    val someArray = arrayOf(1, 2, 3, 1)
    var someSet = mutableSetOf(*someArray)

    println(someSet)

    //accessing elements
    println(someSet.contains(1))
    println(4 in someSet)

    //adding and removing elements
    someSet.add(5)
    val removedOne = someSet.remove(1)
    println(removedOne)
    println(someSet)
}

class OrderLine(val name: String, val price: Int)
class Order(val lines: List<OrderLine>)


fun sum(value: List<Int>): Int {

    return value.reduce { a, b -> a + b }
}

fun mapsAndFlatMap1() {

    var tomato = OrderLine("Tomato", 2)
    var garlic = OrderLine("Garlic", 3)
    var chives = OrderLine("Chives", 4)

    var orderLineList = listOf(tomato, chives, garlic)
    val order = Order(orderLineList)

    val names = order.lines.map { it.name }
    println(names)

    //price
    val totalprice = order.lines.map { it.price }.sum()


    println(totalprice)
//fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
}

fun mapsAndFlatMap2() {

    var tomato = OrderLine("Tomato", 2)
    var garlic = OrderLine("Garlic", 3)

    var chives = OrderLine("Chives", 4)
    var potato = OrderLine("Potato", 4)

    var lettuce = OrderLine("Lettuce", 4)
    var cucumber = OrderLine("Cucumber", 4)

    var list1 = Order(listOf(tomato, garlic)) //list<list<orderLine>>
    var list2 = Order(listOf(chives, potato))
    var list3 = Order(listOf(lettuce, cucumber))

    val orders = listOf(list1, list2, list3)

    val lines = orders.flatMap { it.lines }
    val lines2 = orders.map { it.lines }

}



