package kotlinStudy.lambda.section3.objects

object JsonKeys {
    const val JSON_KEY_ID = "id"
    const val JSON_KEY_FIRSTNAME = "first_name"
    const val JSON_KEY_LASTNAME = "last_name"
}


class Scientist private constructor(
    val id: Int,
    val firstName: String,
    val lastName: String
) {

    companion object {//factory pattern
        var currentId = 0

        fun newScientist(firstName: String, lastName: String):
                Scientist {
            currentId += 1
            return Scientist(currentId, firstName, lastName)
        }
    }

    var fullName = "$firstName $lastName"
}


object ScientistRepository{
    val allScientists = mutableListOf<Scientist>()

    fun addScientist(student : Scientist){
        allScientists.add(student)
    }

    fun removeScientist(student: Scientist){
        allScientists.remove(student )
    }

    fun listAllScientists(){
        allScientists.forEach{
            println("${it.id}: ${it.fullName}")
        }
    }

}

fun main(args: Array<String>) {

    println("oi")
    var pessoa = Scientist.newScientist("joao", "miguel")
}

fun objects() {
    /*
        Instances of classes are called objects.

        Kotlin also can Use uses object to denote a custom type
        for which only a single instance can be created.

        you also can Use object to create anonymous objects, for which,
        multiple instances are created each time the
        anonymous object is used.

        Singletons:
        Restrict a class to have a single instance
        during any given run of an application.

        the object keyword in Kotlin lets you define a type
        that only has a single instance - a named object.

        A type defined with object cannot have constructors:
        Since there is only one instance of an object, there is
        no reason to provide constructor functions to create
        other instances. In a sense, the type is the instance.



     */


}

