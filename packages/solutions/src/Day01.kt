
fun main() {
    fun getElfCapacities(input: List<String>): List<Int> {
        var allElfCapacities = mutableListOf<Int>()
        var currentElfCapacity: Int = 0
        input.forEach {
            if (it.isEmpty()) {
                allElfCapacities.add(currentElfCapacity)
                currentElfCapacity = 0
            } else {
                currentElfCapacity += it.toInt()
            }
        }

        allElfCapacities.add(currentElfCapacity)

        allElfCapacities.sortDescending()

        return allElfCapacities
    }

    fun part1(input: List<String>): Int {
        val allElfCapacities = getElfCapacities(input)

        return allElfCapacities[0]
    }

    fun part2(input: List<String>): Int {
        val allElfCapacities = getElfCapacities(input)
        println(allElfCapacities.subList(0, 3))

        return allElfCapacities.subList(0, 3).reduce { s, t ->
            s + t
        }
    }

    runAdventOfCodeSuite("Day01", ::part1, 24000, ::part2, 45000)
}
