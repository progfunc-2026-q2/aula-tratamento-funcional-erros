package guidelines

import try_examples.parseNumber
import option.divide

// Error when mixing types in for-comprehension (does not compile)
// Uncomment the code below to see the compilation error.
/*
def calculateHeterogeneous(x: String, y: Int): Option[Int] = {
  for {
    num <- parseNumber(x) // Returns Try[Int]
    res <- divide(num, y) // Returns Option[Int] - Compilation ERROR!
  } yield res
}
 */

// Uniformization of types in for-comprehensions
def calculate(x: String, y: Int): Option[Int] = {
  for {
    num <- parseNumber(x).toOption // Converts Try[Int] to Option[Int]
    res <- divide(num, y) // Option[Int] - Compiles successfully!
  } yield res
}

@main def testGuidelines(): Unit = {
  println(calculate("10", 2)) // Some(5)
  println(calculate("abc", 2)) // None
  println(calculate("10", 0)) // None
}
