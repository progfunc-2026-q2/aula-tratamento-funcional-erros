package either

// Error handling with result types
def divide(x: Int, y: Int): Either[String, Int] = {
  if (y == 0) Left("Division by zero")
  else Right(x / y)
}

@main def testDivideEither(): Unit = {
  println(divide(10, 2)) // Right(5)
  println(divide(10, 0)) // Left(Division by zero)
}

// Definition of error types
sealed trait ArithmeticError
case object DivisionByZero extends ArithmeticError
case class NegativeNumberError(value: Int) extends ArithmeticError
case class OverflowError(value: Int) extends ArithmeticError

// Error handling with result types and error types
def dividePositive(x: Int, y: Int): Either[ArithmeticError, Int] = {
  if (y == 0) Left(DivisionByZero)
  else if (x < 0 || y < 0) Left(NegativeNumberError(x))
  else Right(x / y)
}

def multiply(x: Int, y: Int): Either[ArithmeticError, Int] = {
  if (y != 0 && x > Int.MaxValue / y) Left(OverflowError(x))
  else Right(x * y)
}

@main def testDivideEitherWithErrors(): Unit = {
  println(dividePositive(10, 2))
  println(dividePositive(10, 0))
  println(dividePositive(-10, 2))
  println(multiply(10, 2))
  println(multiply(Int.MaxValue, 2))
}

// Monadic composition with Either and ArithmeticError
def calculate(x: Int, y: Int, z: Int): Either[ArithmeticError, Int] = {
  for {
    quotient <- dividePositive(x, y)
    result <- multiply(quotient, z)
  } yield result
}

@main def testMonadicComposition(): Unit = {
  println(calculate(10, 2, 3)) // Right(15)
  println(calculate(10, 0, 3)) // Left(DivisionByZero)
  println(calculate(-10, 2, 3)) // Left(NegativeNumberError(-10))
  println(calculate(Int.MaxValue, 2, 3)) // Left(OverflowError(1073741823))
}
