
/**
 * The cleaner shorthand for printing output.
 */
fun <T, E>runAdventOfCodeSuite(identifier: String, part1: (input: List<String>) -> T, expectedPart1Result: T, part2: (input: List<String>) -> E, expectedPart2Result: E) {

    val testData = readInput("${identifier}_test")
    val realData = readInput(identifier)

    println("---- $identifier (PART 1) ----\n")

    println("Running against test data...")
    val testResult = part1(testData)

    if(testResult != expectedPart1Result) {
        throw Error("Expected: ${expectedPart1Result.toString()}, Actual: ${testResult.toString()}")
    } else {
        println("Passed test case!")
    }

    val realResult = part1(realData)

    println("Real result: $realResult\n")

    if(expectedPart2Result == null) {
        return
    }

    println("---- $identifier (PART 2) ----\n")

    println("Running against test data...")
    val testResult2 = part2(testData)

    if(testResult2 != expectedPart2Result) {
        throw Error("Expected: ${expectedPart2Result}, Actual: ${testResult2.toString()}")
    } else {
        println("Passed test case!")
    }

    val realResult2 = part2(realData)

    println("Real result: $realResult2")
}