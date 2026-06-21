package option

// Error handling with optional types
def divide(x: Int, y: Int): Option[Int] = {
  if (y == 0) None
  else Some(x / y)
}

@main def testDivideOption(): Unit = {
  println(divide(10, 2)) // Some(5)
  println(divide(10, 0)) // None
}

// Unsafe function composition attempt (does not compile)
// The compilation error occurs because the '+' method is not defined for Option[Int].
/*
def expression1(x: Int, y: Int, z: Int): Option[Int] = {
  divide(x, y) + divide(x, z)
}
 */

// Safe function composition with Option via extractor methods
def expression2(x: Int, y: Int, z: Int): Int = {
  divide(x, y).getOrElse(0) + divide(x, z).getOrElse(0)
}

@main def testExpression2(): Unit = {
  println(expression2(10, 2, 3)) // 8
  println(expression2(10, 2, 0)) // 5
}

// Safe function composition with Option via nested flatMap and map
def expression4(x: Int, y: Int, z: Int): Option[Int] = {
  divide(x, y).flatMap { quotient1 =>
    divide(x, z).map { quotient2 =>
      quotient1 + quotient2
    }
  }
}

@main def testExpression4(): Unit = {
  println(expression4(10, 2, 3)) // Some(8)
  println(expression4(10, 2, 0)) // None
}

// Safe function composition with Option via for-comprehensions
def expression5(x: Int, y: Int, z: Int): Option[Int] = {
  for {
    quotient1 <- divide(x, y)
    quotient2 <- divide(x, z)
  } yield quotient1 + quotient2
}

@main def testExpression5(): Unit = {
  println(expression5(10, 2, 3)) // Some(8)
  println(expression5(10, 2, 0)) // None
}

// Partial function for square root using exceptions
def sqrt(x: Double): Double = {
  if (x < 0) throw new IllegalArgumentException("x must be non-negative")

  def babylonian(guess: Double): Double =
    if (Math.abs(guess * guess - x) < 1e-9) guess
    else babylonian((guess + x / guess) / 2)

  if (x == 0) 0.0 else babylonian(1.0)
}

// Total and safe function for square root returning Option
def safeSqrt(x: Double): Option[Double] = {
  if (x < 0) None
  else {
    def babylonian(guess: Double): Double =
      if (Math.abs(guess * guess - x) < 1e-9) guess
      else babylonian((guess + x / guess) / 2)
    Some(if (x == 0) 0.0 else babylonian(1.0))
  }
}

@main def testSafeSqrt(): Unit = {
  println(safeSqrt(9.0)) // Some(3.0)
  println(safeSqrt(-4.0)) // None
}
