package others.delegatedProperties

class Movie {

}

class Projector {

}

class Screen {

}


class MovieTheatre {

    var screen: Screen
    var projector: Projector
    var movie: Movie

    init {
        //Instantiation of dependencies
        //Class Screen is tightly coupled with its dependencies.
        //You should separate the creation of an object from the usage
        //of an object

        screen = Screen()
        projector = Projector()
        movie = Movie()
    }

    /*
        When unit testing, you are only interested in testing
        the behavior of that class, not any of its dependencies.
        To avoid testing dependencies in the MovieTheatre unit tests,
        you will make a mock objects out of you dependencies.
        You can pass these mock objects from de outside, so when
        the tests execute, MovieTheatre will call the methos on the mock
        object rather than a real object.
     */
    fun playMovie() {
        System.out.println("Playing movie now")
    }
}


//Injection dependencies

/*
    It's important to pass dependencies from an external source
    rather than creating them within a class;
 */
class MovieTheatreID(
    val screen: Screen,
    val projector: Projector,
    val movie: Movie
) {

    fun playMovie() {
        System.out.println("Playing movie now")
    }

    /*
       MovieTheatreId is not responsible for creating it own dependencies.
       Instead it receives its dependencies as parameters when it's constructed

     */
}


fun main() {
    println("Inversion dependency")

    //1 Instantiate dependencies
    val screen = Screen()
    val projector = Projector()
    val movie = Movie()

    //2 Instantiate MovieTheatre, passing in dependencies
    val movieTheatre = MovieTheatreID(screen, projector, movie)

    //3 Call methods on the MovieTheatre
    movieTheatre.playMovie()

    /*
        Suppose Screen has its own dependencies like Curtain and backdrop
        While projector has its own dependencies like lens, powerCord and Reel.
        Each of these dependencies need to be instantiated and passed
        into respective class upon creation of that class and then Screen,
        projector and movie need to be passed in to create a MovieTheatre object.
        The result is a lot  of boilerplate code.

        If it takes too many lines of code before you can instantiate you
        main class, it's a sign that a dependency injection framework may help.

    */
}

/*
  Dependency Injection frameworks

  If an app has overly complicated dependencies, consider
  using a thidr party frameworkd like Koin, Proton, Feather, Tiger,
  LighSabaer, Transfuse or Dagger 2.

  Dagger 2 for exeample:
  @Inject: marks which dependencies to inject
  @Component: is used on an interface class from which Dagger will
  then generate a new class that contai methods that return objects with their
  dependencies injected.

  class Projector @Inject constructor()
  class Screen @Inject constructor()
  class Movie @Inject constructor()
  class MovieTheatre @Inject constructor(val screen: Screen, val projector: Projector, val movie: Movie) {
  ...

  @Component
    interface MovieTheatreComponent {
      fun getMovieTheatre() : MovieTheatre
    }

    In this interface, there's a single method
    wich return an instance of MovieTheatre.

    Rebuild the project. Dagger will generate a class for you
    named DaggerMovieTheatreComponent with all of the
    dependency injection code added using the @Inject annotations
    as its guide.




}





 */