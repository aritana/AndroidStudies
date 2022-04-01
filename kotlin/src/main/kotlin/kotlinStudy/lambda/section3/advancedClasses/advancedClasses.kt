package kotlinStudy.lambda.section3

data class Grade(val letter: Char, val points: Double, val credits: Double)


open class Person(var firstName: String, var lastName: String) {
    fun fullName() = "$firstName $lastName"
}

open class Student(
    firstName: String, lastName: String,
    var grades: MutableList<Grade> = mutableListOf<Grade>()
) : Person(firstName, lastName) {

    open fun recordGrade(grade: Grade) {
        grades.add(grade)
    }

    open fun teste() {
        println("teste")
    }
}

open class BandMember(firstName: String, lastName: String) : Student(firstName, lastName) {

    open val minimumPracticeTime: Int
        get() {
            return 2
        }
}

class OboePlayer(firstName: String, lastName: String) : BandMember(firstName, lastName) {

    override val minimumPracticeTime: Int
        get() = super.minimumPracticeTime * 2
}

class StudentAthlete(firstName: String, lastName: String) : Student(firstName, lastName) {
    val failedClasses = mutableListOf<Grade>()

    override fun recordGrade(grade: Grade) {
        super.recordGrade(grade)

        if (grade.letter == 'F') {
            failedClasses.add(grade)
        }
    }

    val isEligible: Boolean
        get() = failedClasses.size < 3
}


fun main() {

    println("advanced classe")
    //inheritance()
    //polymorphism()
    // runtimeHierachyChecks()
    //inheritanceMethodsAndOverrides()
    //abstractClasses()
    //sealedClasses()
    visibilityModifiers()


}

fun inheritance() {

    val gradeList = (0..10).map {
        Grade('A', 2.0, 3.0)
    }
    val gradeMultableList = gradeList.toMutableList()
    var student: Student = Student("Marina", "Alves", gradeMultableList)

    println(student.fullName())
    println(gradeList)

    val john = Student(firstName = "Johnny", lastName = "Appleseed")
    println(john.fullName())

}

fun polymorphism() {

    val person = Person(firstName = "Johnny", lastName = "Appleseed")
    val oboePlayer = OboePlayer(firstName = "Jane", lastName = "Appleaseed")

    var person1 = phonebookName(person)
    var person2 = phonebookName(oboePlayer)

    println(person1)
    println(person2)


}

fun phonebookName(person: Person): String {
    return "${person.lastName}, ${person.firstName}"
}


fun runtimeHierachyChecks() {

    val oboePlayer = OboePlayer(firstName = "Jane", lastName = "Appleaseed")
    var hallMonitor = Student(firstName = "Jill", lastName = "Bananapeel")
    hallMonitor = oboePlayer //the compiler won't allow you to attempt
    //calling properties or methods for a more derived type.

    println(hallMonitor is OboePlayer)
    println(hallMonitor is Person)

    /*
        Kotlin also provides the as infix operator to treat a property
        or a variable as another type:
        as: an unsfafe cast to a specific type that is known at compile time
        to suceed, such as casting to a supertype.

        as?: A safe cast to a subtype. If the cast fails, the result
        of the expression will be null
     */
    //(oboePlayer as Student).minimumPracticeTime // Error: No longer a band member!

    (hallMonitor as? BandMember)?.minimumPracticeTime
    // 4 if hallMonitor = oboePlayer was run, else null

    println((hallMonitor as? BandMember)?.minimumPracticeTime)

    println(afterClassActivity(oboePlayer))

    /**
     * Kotlin has a strong type system, and the interpretation
     * of a specific type can have an effect on static dispatch,
     * or the decision of which specific operation is selected
     * at compile time. Sound confusion? How about an example.
     */

    println(afterClassActivity(oboePlayer as Student))

}

fun afterClassActivity(student: Student): String {
    return "Goes home!"
}

fun afterClassActivity(student: BandMember): String {
    return "Goes to practice!"
}

fun inheritanceMethodsAndOverrides() {
    val math = Grade(letter = 'B', points = 9.0, credits = 3.0)
    val science = Grade(letter = 'F', points = 9.0, credits = 3.0)
    val physics = Grade(letter = 'F', points = 9.0, credits = 3.0)
    val chemistry = Grade(letter = 'F', points = 9.0, credits = 3.0)

    val dom = StudentAthlete(firstName = "Dom", lastName = "Grady")
    dom.recordGrade(math)
    dom.recordGrade(science)
    dom.recordGrade(physics)
    println(dom.isEligible) // > true
    dom.recordGrade(chemistry)
    println(dom.isEligible) // > false

    dom.teste()

}


fun abstractClasses() {
//To prevent a class for being instantiated, but still
//be able to be inherited from.
//This will let you define properties and behavior common
//to all subclasses.

//you can only create instances of the subclasses and not the base, parent class
//Such parent classes are called abstract.

//Classes declared with the abstract keyword are open by default
//and can be inherited from
//In abstract classes, you can also declare abstract methods
//marked with abstract that have no body which must have be overriden in subclasses.


    abstract class Mammal(val birthDate: String) {
        abstract fun consumeFood()
    }

    class Human(birthDate: String) : Mammal(birthDate) {
        override fun consumeFood() {
            TODO("Not yet implemented")
        }

        fun createBirthCertficate() {

        }
    }

    val human = Human("1/1/2000")
}


sealed class Shape {

    class Circle(val radius: Int) : Shape()
    class Square(val sideLength: Int) : Shape()


}


fun sealedClasses() {
    /*
        Sealed classes are useful when you want to make
        sure that the values of a given type can only come
        from a particular limited set of subtypes.
        They allow you to define a strict hierarchy of types.
        The sealed classes themselves are abstract and cannot be intantiated.

        Sealed classes act very much like enum classes. But also
        allow subytpes which can have multiples instances and have
        state
     */

    val circle1 = Shape.Circle(4)
    val circle2 = Shape.Circle(2)

    val square1 = Shape.Square(4)
    val square2 = Shape.Square(2)


    fun size(shape: Shape): Int {
        return when (shape) {
            is Shape.Circle -> shape.radius
            is Shape.Square -> shape.sideLength
            else -> {
                1
            }
        }
    }

    println(size(circle1))
}

fun secondaryConstructors() {

    class Person2(var firstName: String, var lastName: String) {
        fun fullName() = "$firstName $lastName"
    }

// is the same as

    class Person3 constructor(var firstName: String, var lastName: String) {
        fun fullName() = "$firstName $lastName"

        constructor(firstName: String, lastName: String, size: Int) : this(firstName, lastName)
    }

}

//nested class
class Car(val carName: String) {
    class Engine(val engineName: String) {
        override fun toString(): String {
            //      return "$engineName in a $carName" // Error: cannot see outer scope!
          return "error"
        }
    }
}

class Car3(val carName: String) {
    class Engine(val engineName: String)
}


//inner class
class Car2(val carName: String) {
    inner class Engine(val engineName: String) {
        override fun toString(): String {
            return "$engineName in a $carName" // Error: cannot see outer scope!
        }
    }
}


fun nestedClasses() {

    val mazda = Car2("mazda")
    val mazdaEngine = mazda.Engine("rotary")
    println(mazdaEngine) // > rotary engine in a mazda

    val fiat = Car3("fiat")
    val fiatMotor = Car3.Engine("fsfs")

}



data class Privilege(val id: Int, val name: String)

open class User(val username: String, private val id: String, protected var age: Int)

class PrivilegedUser(username: String, id: String, age: Int): User(username, id, age) {
    private val privileges = mutableListOf<Privilege>()

    fun addPrivilege(privilege: Privilege) {
        privileges.add(privilege)
    }

    fun hasPrivilege(id: Int): Boolean {
        return privileges.map { it.id }.contains(id)
    }

    fun about(): String {
        //return "$username, $id" // Error: id is private
        return "$username, $age" // OK: age is protected
    }
}

fun visibilityModifiers(){

    /*
        public: visible from everywhere
        private: visible only within the same classses,
        and only within the same file for top-level functions
        and other non=class definitions
        protected: visible only within subclasses for class hierarchies
        internal: visible only within the same module
     */

    val privilegedUser = PrivilegedUser(username = "sashinka", id = "1234", age = 21)
    val privilege = Privilege(1, "invisibility")
    privilegedUser.addPrivilege(privilege)
    println(privilegedUser.about()) // > sashinka, 21

}

//challenges