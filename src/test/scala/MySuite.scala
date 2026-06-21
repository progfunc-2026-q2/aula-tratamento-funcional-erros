class MySuite extends munit.FunSuite {
  
  test("Option division") {
    assertEquals(option.divide(10, 2), Some(5))
    assertEquals(option.divide(10, 0), None)
  }

  test("Option compositions") {
    assertEquals(option.expression2(10, 2, 3), 8)
    assertEquals(option.expression2(10, 2, 0), 5)
    
    assertEquals(option.expression4(10, 2, 3), Some(8))
    assertEquals(option.expression4(10, 2, 0), None)

    assertEquals(option.expression5(10, 2, 3), Some(8))
    assertEquals(option.expression5(10, 2, 0), None)
  }

  test("safeSqrt") {
    assertEquals(option.safeSqrt(9.0), Some(3.0))
    assertEquals(option.safeSqrt(-4.0), None)
  }

  test("Either division and custom errors") {
    assertEquals(either.divide(10, 2), Right(5))
    assertEquals(either.divide(10, 0), Left("Division by zero"))

    assertEquals(either.dividePositive(10, 2), Right(5))
    assertEquals(either.dividePositive(10, 0), Left(either.DivisionByZero))
    assertEquals(either.dividePositive(-10, 2), Left(either.NegativeNumberError(-10)))
  }

  test("Either composition (calculate)") {
    assertEquals(either.calculate(10, 2, 3), Right(15))
    assertEquals(either.calculate(10, 0, 3), Left(either.DivisionByZero))
    assertEquals(either.calculate(-10, 2, 3), Left(either.NegativeNumberError(-10)))
  }

  test("Try parsing and composition") {
    assert(try_examples.parseNumber("123").isSuccess)
    assertEquals(try_examples.parseNumber("123").get, 123)
    assert(try_examples.parseNumber("abc").isFailure)

    assertEquals(try_examples.divideStrings("10", "2").get, 5)
    assert(try_examples.divideStrings("10", "0").isFailure)
    assert(try_examples.divideStrings("abc", "2").isFailure)
  }

  test("Guidelines uniformization") {
    assertEquals(guidelines.calculate("10", 2), Some(5))
    assertEquals(guidelines.calculate("abc", 2), None)
    assertEquals(guidelines.calculate("10", 0), None)
  }
}
