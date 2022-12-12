class CrateColumns {

    private val columns = mutableMapOf<Int, ArrayDeque<Char>>()

    fun init(input: List<String>) {
        input.forEach {
            for (i in 1 until it.length step 4) {
                if (it[i].toString().trim().isNotEmpty()) {
                    placeBlock((i - 1) / 4, it[i])
                }

            }
        }
    }

    fun moveBlocksOneByOne(from: Int, to: Int, amount: Int) {
        for (i in 0 until amount) {
            placeBlock(to, removeBlock(from))
        }

    }

    fun moveBlocksAsStack(from: Int, to: Int, amount: Int) {
        val fromColumn = columns.get(from) as ArrayDeque<Char>
        val toColumn = columns.get(to) as ArrayDeque<Char>

        val toAdd = arrayListOf<Char>()

        for (i in 0 until amount) {
            toAdd.add(0, fromColumn.removeFirst())
        }

        toAdd.forEach {
            toColumn.addFirst(it)
        }
    }

    fun getTopOfColumns(): String {
        var res = ""
        var i = 0
        while (columns[i] != null) {
            res += columns[i]!![0]
            i++
        }

        return res
    }

    private fun placeBlock(index: Int, contents: Char) {
        if (columns[index] == null) {
            columns[index] = ArrayDeque<Char>()
        }

        columns[index]!!.addFirst(contents)

    }

    private fun removeBlock(index: Int): Char {
        if (columns[index] != null) {
            return columns[index]!!.removeFirst()
        }
        throw Error("column hasn't been initialized")
    }

    override fun toString(): String {
        return columns.toString()
    }
}

fun main() {

    fun part1(input: List<String>): String {
        val crateColumns = CrateColumns();
        var setupEndIndex: Int = 0
        for (i in input.indices) {
            if (setupEndIndex in 1 until i) {
//                println("Parsing: ${input[i]}")
                val regex = Regex("move (.+) from (.+) to (.+)")
                val result = regex.find(input[i])
                val (amount, from, to) = result!!.destructured
                crateColumns.moveBlocksOneByOne(from.toInt() - 1, to.toInt() - 1, amount.toInt())
            }
            if (input[i].isEmpty()) {
                setupEndIndex = i
                // Build the towers!
                crateColumns.init(input.subList(0, i - 1).reversed())

            }
        }

        return crateColumns.getTopOfColumns()
    }

    fun part2(input: List<String>): String {
        val crateColumns = CrateColumns();
        var setupEndIndex: Int = 0
        for (i in input.indices) {
            if (setupEndIndex in 1 until i) {
//                println("Parsing: ${input[i]}")
                val regex = Regex("move (.+) from (.+) to (.+)")
                val result = regex.find(input[i])
                val (amount, from, to) = result!!.destructured
                crateColumns.moveBlocksAsStack(from.toInt() - 1, to.toInt() - 1, amount.toInt())
            }
            if (input[i].isEmpty()) {
                setupEndIndex = i
                // Build the towers!
                crateColumns.init(input.subList(0, i - 1).reversed())

            }
        }

        return crateColumns.getTopOfColumns()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    part1(testInput).println()
    check(part1(testInput) == "CMZ")

    val input = readInput("Day05")

    part1(input).println()

    part2(testInput).println()
    check(part2(testInput) == "MCD")
    part2(input).println()

}

