package try_examples

import scala.util.{Try, Success, Failure}

// Using the Try type to capture exceptions
def parseNumber(s: String): Try[Int] = {
  Try(s.toInt)
}

@main def testTry(): Unit = {
  println(parseNumber("123")) // Success(123)
  println(parseNumber("abc")) // Failure(java.lang.NumberFormatException: For input string: "abc")

  parseNumber("abc") match {
    case Success(value) => println(s"Read number: $value")
    case Failure(error) => println(s"Error detected: ${error.getMessage}")
  }
}

// Monadic composition with the Try type
def divideStrings(s1: String, s2: String): Try[Int] = {
  for {
    n1 <- Try(s1.toInt)
    n2 <- Try(s2.toInt)
  } yield n1 / n2
}

@main def testTryComposition(): Unit = {
  println(divideStrings("10", "2"))
  println(divideStrings("abc", "2"))
  println(divideStrings("10", "abc"))
  println(divideStrings("10", "0"))
}
