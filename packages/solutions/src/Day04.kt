class Range {
    var min: Int
    var max: Int

    constructor(min: Int, max: Int) {
        this.min = min;
        this.max = max
    }

    fun fullyContains(range: Range): Boolean {
        return (this.min <= range.min && this.max >= range.max)
    }

    fun overlaps(range: Range): Boolean {
        return (this.min <= range.min && this.max >= range.min) || (this.max >= range.max && this.min <= range.max)
    }
}


fun main() {


    fun getRanges(line: String): List<Range> {
        var ranges = mutableListOf<Range>();

        var rangeStrings = line.split(",");
        rangeStrings.forEach {
            val bounds = it.split("-")
            ranges.add(Range(bounds[0].toInt(), bounds[1].toInt()))
        }

        return ranges
    }

    fun part1(input: List<String>): Int {
        var subsets = 0;

        input.forEach {
            val ranges = getRanges(it)

            if (ranges[0].fullyContains(ranges[1]) || ranges[1].fullyContains(ranges[0])) {
                subsets += 1
            }
        }

        return subsets
    }

    fun part2(input: List<String>): Int {
        var subsetsThatOverlap = 0;

        input.forEach {
            val ranges = getRanges(it)

            if (ranges[0].overlaps(ranges[1]) || ranges[1].overlaps(ranges[0])) {
                subsetsThatOverlap += 1
            }
        }

        return subsetsThatOverlap
    }

    runAdventOfCodeSuite("Day04", ::part1, 2, ::part2, 4)

}
