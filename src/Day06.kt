fun main() {
    fun getStartOfPacketMarkerIndex(str: String): Int {
        for(i in str.indices) {
            if(i <= 3) {
                continue
            }

            val section = str.substring(i-4, i)
//            section.println()
            // if last 4 characters are unique
            if(section.all(hashSetOf<Char>()::add)) {
                return i
            }

        }

        return -1
    }

    fun getStartOfMessageMarkerIndex(str: String): Int {
        for(i in str.indices) {
            if(i < 14) {
                continue
            }

            val section = str.substring(i-14, i)
//            section.println()
            // if last 4 characters are unique
            if(section.all(hashSetOf<Char>()::add)) {
                return i
            }

        }

        return -1
    }

    fun part1(input: List<String>): Int {
        var totalIndex = 0;
        input.forEach {
            val startOfPacket = getStartOfPacketMarkerIndex(it)
//            startOfPacket.println()
            totalIndex += startOfPacket

        }
        return totalIndex
    }

    fun part2(input: List<String>): Int {
        var totalIndex = 0;
        input.forEach {
            val startOfPacket = getStartOfMessageMarkerIndex(it)
//            startOfPacket.println()
            totalIndex += startOfPacket

        }
        return totalIndex    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 39)

    val input = readInput("Day06")
    part1(input).println()

    check(part2(testInput) == 120)
    part2(input).println()
}
