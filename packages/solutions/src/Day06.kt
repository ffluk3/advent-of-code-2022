fun main() {
    fun getStartOfPacketMarkerIndex(str: String): Int {
        for (i in str.indices) {
            if (i <= 3) {
                continue
            }

            val section = str.substring(i - 4, i)
//            section.println()
            // if last 4 characters are unique
            if (section.all(hashSetOf<Char>()::add)) {
                return i
            }

        }

        return -1
    }

    fun getStartOfMessageMarkerIndex(str: String): Int {
        for (i in str.indices) {
            if (i < 14) {
                continue
            }

            val section = str.substring(i - 14, i)
//            section.println()
            // if last 4 characters are unique
            if (section.all(hashSetOf<Char>()::add)) {
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
        return totalIndex
    }


    runAdventOfCodeSuite("Day06", ::part1, 39, ::part2, 120)

}
