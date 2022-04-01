package section3

import com.sun.xml.internal.ws.wsdl.writer.document.http.Address
import java.util.*


/*
    types are both data and behavior. Data takes the form of properties
    and behavior is implemented using functions called methods.
 */

class Person(var firstName: String, var lastName: String) { //primary constructor
    //everything here is a member of the class
    val fullName
        get() = "$firstName $lastName"
}

class Grade(val letter: String, val points: Double, val credits: Double)

class Student(
    val firstName: String,
    val lastName: String,
    val grades: MutableList<Grade> = mutableListOf(),
    var credits: Double = 0.0,
    var points: Double = 0.0
) {

    val gradePointAverage
        get() = points / credits

    fun recordGrade(grade: Grade) {
        grades.add(grade)
        credits += grade.credits
        points += grade.points
    }
}

class Student2(var firstName: String, var lastName: String, var id: Int) {

    override fun hashCode(): Int {
        val prime = 31
        var result = 1

        result = prime * result + firstName.hashCode()
        result = prime * result + id
        result = prime * result + lastName.hashCode()

        return result
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Student2

        if (firstName != other.firstName) return false
        if (lastName != other.lastName) return false
        if (id != other.id) return false

        return true
    }

    override fun toString(): String {
        return "Student2(firstName=$firstName, lastName='$lastName', id=$id)"
    }

    fun copy(
        firstName: String = this.firstName,
        lastName: String = this.lastName,
        id: Int = this.id
    ) = Student2(firstName, lastName, id)

}

data class StudentData(var firstName: String, var lastName: String, var id: Int)


fun main(args: Array<String>) {

    /*
        In Kotlin, an instance of a class is a mutable object
        Classes are reference types, and a variale of a class types
        does not store the actual instance, but a reference to a location
        in memory that stores the instance
     */
    // comparator2()
    // methodsAndMutability()
    // dataClasses()
    // destructuringDeclarations()
    challenge1()

}

fun comparator() {
    val impostorJohn = Person(firstName = "John", lastName = "Appleseed")
    val john = Person(firstName = "John", lastName = "Appleseed")
    john.firstName = "kkjkjk"
    var homeOwner = john
    john.firstName = "John"

    // println(john === homeOwner) // true
    println(john.firstName == impostorJohn.firstName) // false
    println(john == impostorJohn) // ?
    //println(impostorJohn === homeOwner) // false

// Assignment of existing variables changes the instances the variables reference.
    homeOwner = impostorJohn
    // println( john === homeOwner) // false

    homeOwner = john
    //  println(john === homeOwner) // true
}

fun comparator2() {

    val john = Person(firstName = "John", lastName = "Appleseed")

    val imposters = (0..100).map {
        Person(firstName = "John", lastName = "Appleseed")
    }

    val map = imposters.map {
        it.firstName == "Jonh" && it.lastName == "Appleseed"
    }.contains(true)

    println(map)
    //println(imposters.contains(john))
    println("Member Of?")
    println(memberOf(john, imposters))

    //hide the 'real' John somewhere among the imposters.

    val mutableImposters = mutableListOf<Person>()
    mutableImposters.addAll(imposters)
    mutableImposters.contains(john)
    mutableImposters.add(Random().nextInt(5), john)

    //John can now be found among the imposters.
    //println(mutableImposters.contains(john))
    println("Member Of?")
    println(memberOf(john, mutableImposters))

    /*Since 'Person' is a reference type, you can use === to grab the real John
     out of the list of imposters and modify the value.
     the original 'jonh' variable will print the new last name!
    */

    val indeOfJonh = mutableImposters.indexOf(john)
    if (indeOfJonh != -1) {
        mutableImposters[indeOfJonh].lastName = "Bananapeel"
    }
    println(john.fullName)

}

fun memberOf(person: Person, group: List<Person>): Boolean {

    return group.contains(person)

}


fun methodsAndMutability() {

    val jane = Student(firstName = "Jane", lastName = "Appleseed")
    val history = Grade(letter = "B", points = 9.0, credits = 3.0)
    var math = Grade(letter = "A", points = 16.0, credits = 4.0)


    jane.recordGrade(history)
    jane.recordGrade(math)

    math = Grade(letter = "A", points = 20.0, credits = 5.0)
    jane.recordGrade(math)


    println(jane.gradePointAverage)

}

fun dataClasses() {

    val albert = Student2(firstName = "Albert", lastName = "Einstein", id = 1)
    val richard = Student2(firstName = "Richard", lastName = "Feynman", id = 2)
    val albertCopy = albert.copy()
    val albertReferenceCopy = albert

//    println(albert)  // > Student (firstName=Albert, lastName=Einstein, id=1)
//    println(richard) // > Student (firstName=Richard, lastName=Feynman, id=2)
//    println(albert == richard) // > false
//    println(albert == albertCopy) // > true
//    println(albert === albertCopy) // > false
//    println(albert === albertReferenceCopy) // > true

    //==:operator compares the values in the objects using equals()
    // ===:  compares the identity of the references

    val marie = StudentData("Marie", "Curie", id = 1)
    val emmy = StudentData("Emmy", "Noether", id = 2)
    val marieCopy = marie.copy()

    println(marie) // > StudentData(firstName=Marie, lastName=Curie, id=1)
    println(emmy)  // > StudentData(firstName=Emmy, lastName=Noether, id=2)
    println(marie == emmy) // > false
    println(marie == marieCopy) // > true
    println(marie === marieCopy) // > false

}

fun destructuringDeclarations() {

    //you can extract the data inside of a data class using a
    //destructuring declaration. Just assign a variable to each
    //of the properties of the data class in one assignment statement.
    val marie = StudentData("Marie", "Curie", id = 1)
    val (firstName, lastName, id) = marie

    println(firstName) // > Marie
    println(lastName)  // > Curie
    println(id)        // > 1
}

//challenges

fun challenge1() {

//to create lists of movies and share with others users.

//create a user class and a movie list

    class MovieList(var name: String, var movies: MutableList<String>) {

        fun print() {
            movies.forEach { item ->
                println(item)
            }
        }
    }


    data class User(var name: String) {

        val movies = mutableMapOf<String, MovieList>()

        fun addList(list: MovieList?) {
            if (list != null) {
                movies.put(list.name, list)
            }
        }

        fun list(name: String): MovieList? {

            return movies.get(name)
        }
    }

    var moviesA = mutableListOf<String>("Armagedon", "Eclipse", "Q13")
    var moviesB = mutableListOf<String>("TMonica", "Live", "RaulGilLife")

    val jane = User("Jane")
    val jb = User("JB")
    var movieList1 = MovieList(jane.name, moviesA)
    var movieList2 = MovieList(jb.name, moviesB)

    jane.addList(movieList2)
    jb.addList(movieList2)

    var list = jb.list("JB")
    if (list != null) {
        list.print()
    }

}

fun challenge2() {

//    data class TShirt(var size: String, var color: String, var price: Double, var image: String)
//
//    data class User(var name: String, var email: String, var shoppingCart: ShoppingCart)
//
//    class ShoppingCart( var user:User, var address: Address) {
//        var order : Int =
//        var items = mutableListOf<TShirt>()
//
//        override fun hashCode(): Int {
//            val prime = 31
//            var result = 1
//
//            result = prime * result + user..hashCode()
//            result = prime * result + id
//            result = prime * result + lastName.hashCode()
//
//            return result
//        }
//    }
//
//
//
//    data class Address(
//        var name: String,
//        var street: String,
//        var city: String,
//        var zipCode: String,
//    )


}