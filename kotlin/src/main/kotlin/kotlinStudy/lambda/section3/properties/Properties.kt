package kotlinStudy.lambda.section3.properties

import kotlin.math.roundToInt
import kotlin.properties.Delegates

class IceCream {
    val name: String = "Fernando"

}


class Contact(var fullName: String, var emailAddress: String)

class Contact3(
    var fullName: String,
    val emailAddress: String,
    var type: String = "Friend"
)

//Add a FuelTank property to Car and fill the tank.
//Then drive around for a while.
class Car(
    val make: String,
    val color: String,
    val fuelTank: FuelTank
)

class FuelTank {
    var level by Delegates.observable(0.0) {// decimal percentage between 0 and 1
            _, _, new ->

        lowFuel = new < 0.10
        println("Low Fuel!!")
        //Ensure that when the tank fills back up, the lowFuel warning will turn off.
    }

    //Add a lowFuel property of Boolean type to the class.
    var lowFuel: Boolean = false
    //Flip the lowFuel Boolean when the level drops below 10%.
}


class Contact4(
    var firstName: String, var lastName: String
) {
    var fullName = "$firstName $lastName"
}

class Address {
    var address1: String
    var address2: String? = null
    var city = ""
    var state: String

    init {
        address1 = "rua campo"
        state = "mg"
    }
}

class TV(var height: Double, var width: Double) {

    var diagonal: Int
        get() {
            val result = Math.sqrt(height * height + width * width)
            return result.roundToInt()
        }
        set(value) {
            val ratioWidth = 16.0
            val ratioHeight = 9.0

            val ratioDiagonal = Math.sqrt(ratioWidth * ratioWidth + ratioHeight * ratioHeight)
            height = value.toDouble() * ratioHeight / ratioDiagonal
            width = height * ratioWidth / ratioHeight
        }
}

class Level(val id: Int, var boss: String, var unlocked: Boolean) {
    companion object {
        @JvmStatic
        var highestLevel = 1
    }
}

class DelegatedLevel(val id: Int, var boss: String) {
    companion object {
        var highestLevel = 1
    }

    var unlocked: Boolean by Delegates.observable(false) { _, old, new ->
        if (new && id > highestLevel) {
            highestLevel = id
        }
        println("$old -> $new")
    }
}

class LightBulb {
    companion object {
        const val maxCurrent = 40
    }

    var current by Delegates.vetoable(0) { _, _, new ->
        if (new > maxCurrent) {
            println("Current too high, falling back to previous setting.")
            false
        } else {
            true
        }
    }
}

class Circle(var radius: Double = 0.0) {
    val pi: Double by lazy {
        ((4.0 * Math.atan(1.0 / 5.0)) - Math.atan(1.0 / 239.0)) * 4.0
    }
    val circumference: Double
        get() = pi * radius * 2


}


fun main(args: Array<String>) {

    val contact = Contact(fullName = "Grace Murray", emailAddress = "grace@navy.mil")
    val name = contact.fullName // Grace Murray
    val email = contact.emailAddress // grace@navy.mil

    contact.fullName = "Grace Hopper"
    val grace = contact.fullName // Grace Hopper


    val contact3 =
        Contact3(fullName = "Jose", emailAddress = "pereira@silvania.com", type = "family")
    val contact4 = Contact4(firstName = "Jose", lastName = "pereira")

    var address = Address()
    var tv = TV(4.0, 3.0)

    println(tv.diagonal)
    tv.diagonal = 5

    val level1 = Level(id = 1, boss = "Chameleon", unlocked = true)
    val level2 = Level(id = 2, boss = "Squid", unlocked = false)
    val level3 = Level(id = 3, boss = "Chupacabra", unlocked = false)
    val level4 = Level(id = 4, boss = "Yeti", unlocked = false)

    val delegatedlevel1 = DelegatedLevel(id = 1, boss = "Chameleon")
    val delegatedlevel2 = DelegatedLevel(id = 2, boss = "Squid")

    // println(DelegatedLevel.highestLevel) // 1

    delegatedlevel2.unlocked = true

    // println(DelegatedLevel.highestLevel) // 2

    val light = LightBulb()
    light.current = 50
    var current = light.current // 0
    light.current = 40
    current = light.current // 40


    val circle = Circle(5.0) // got a circle, pi has not been run
    val circumference = circle.circumference // 31.42

    println(circumference)


    var car =  Car("Ford","Blue", FuelTank() )
    car.fuelTank.level = 0.5



}

