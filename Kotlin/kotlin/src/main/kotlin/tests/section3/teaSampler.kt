package tests.section3

fun main(){

    whenPassingNull_shouldReturnNull1()
    whenPassingNotNullShouldReturnNotNull2()
    whenPassingToasterShouldContainsToaster3()
}

fun whenPassingNull_shouldReturnNull1(){

    val nullResult = getSearchUrl(null)
    if(nullResult == null){
        println("1-Success")
    }else{
        throw  java.lang.AssertionError("1-Result was not null")
    }
}

fun whenPassingNotNullShouldReturnNotNull2(){

    val nonNullResult = getSearchUrl("toaster")

    if(nonNullResult != null){
        println("2-Success")
    }else{
        throw  java.lang.AssertionError("2-Result was null")
    }

}

fun whenPassingToasterShouldContainsToaster3(){

    val result =  getSearchUrl("toaster")
    if(result?.contains("toaster")==true){
        println("3-Success")
    }else{
        throw java.lang.AssertionError("3-Result did not contain query")
    }
}

fun getSearchUrl(query:String?):String?{

    return query?.let{"https://www.google.com/search?q=$query"}

}
