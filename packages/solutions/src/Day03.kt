fun main() {
    fun getScore(char: Char): Int {
        if (char.isUpperCase()) {
            return char.code + 26 - 64
        } else {
            return char.code - 96
        }
    }

    fun getCommonLetters(str1: String, str: String): List<Char> {
        val res = mutableSetOf<Char>()
        str1.toList().forEach {
            if (str.contains(it, false)) {
                res.add(it)
            }
        }

        return res.toList()
    }

    fun getCommonLetters(chars: List<Char>, str: String): List<Char> {
        val res = mutableSetOf<Char>()
        chars.forEach {
            if (str.contains(it, false)) {
                res.add(it)
            }
        }

        return res.toList()
    }

    fun part1(input: List<String>): Int {
        var score = 0
        input.forEach {
            val front = it.substring(0, it.length / 2);
            val back = it.substring(it.length / 2, it.length)

            println(it + " " + front + " " + back)
            var scoreAdded = false

            front.forEach { it2 ->

                if (back.contains(it2, false) && !scoreAdded) {
                    scoreAdded = true

                    val scoreToAdd = getScore(it2)
                    println(it2 + " " + scoreToAdd)
                    score += scoreToAdd

                }
            }

        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0

        for (i in 0 until input.size step 3) {
            var commonLetters = listOf<Char>()
            commonLetters = getCommonLetters(input[i], input[i + 1])
            commonLetters = getCommonLetters(commonLetters, input[i + 2]);

            score += getScore(commonLetters[0])

        }

        return score
    }

    runAdventOfCodeSuite("Day03", ::part1, 157, ::part2, 70)

}
