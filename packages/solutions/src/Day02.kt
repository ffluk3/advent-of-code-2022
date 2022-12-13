import java.lang.Error

enum class OpponentMove(val str: String) {
    A("rock"),
    B("paper"),
    C("scissors")

}

enum class PlayerMove(val str: String) {
    X("rock"),
    Y("paper"),
    Z("scissors")
}

enum class EndResult(val str: String) {
    X("lose"),
    Y("draw"),
    Z("win")
}

fun main() {

    fun getMoveScore(str: String): Int {
        when (str) {
            "rock" -> return 1
            "paper" -> return 2
            "scissors" -> return 3
            else -> {
                println("oop")
            }
        }

        return 0
    }

    fun getWinningPlayerMove(opponentMove: OpponentMove): PlayerMove {
        val move = when (opponentMove.str) {
            "rock" -> PlayerMove.Y
            "paper" -> PlayerMove.Z
            "scissors" -> PlayerMove.X
            else -> throw Error("invalid move")
        }

        return move
    }

    fun getLosingPlayerMove(opponentMove: OpponentMove): PlayerMove {
        val move = when (opponentMove.str) {
            "rock" -> PlayerMove.Z
            "paper" -> PlayerMove.X
            "scissors" -> PlayerMove.Y
            else -> throw Error("invalid move")
        }

        return move
    }

    fun getDrawPlayerMove(opponentMove: OpponentMove): PlayerMove {
        val move = when (opponentMove.str) {
            "rock" -> PlayerMove.X
            "paper" -> PlayerMove.Y
            "scissors" -> PlayerMove.Z
            else -> throw Error("invalid move")
        }

        return move
    }

    fun playerWonRound(opponentMove: OpponentMove, playerMove: PlayerMove): Boolean {
        return (opponentMove.str === "rock" && playerMove.str === "paper") ||
                (opponentMove.str === "paper" && playerMove.str === "scissors") ||
                (opponentMove.str === "scissors" && playerMove.str === "rock")
    }

    fun roundWasADraw(opponentMove: OpponentMove, playerMove: PlayerMove): Boolean {
        return playerMove.str == opponentMove.str
    }

    fun part1(input: List<String>): Int {
        var playerScore = 0;

        input.forEach {
            val parts = it.split(" ")
            val opponentMove = enumValueOf<OpponentMove>(parts[0])
            val playerMove = enumValueOf<PlayerMove>(parts[1])

            playerScore += getMoveScore(playerMove.str)

            if(roundWasADraw(opponentMove,playerMove)) {
                playerScore += 3
            }
            else if(playerWonRound(enumValueOf<OpponentMove>(parts[0]), enumValueOf<PlayerMove>(parts[1]))) {
                playerScore += 6;
            }
        }

        return playerScore
    }

    fun part2(input: List<String>): Int {
        var playerScore = 0;

        input.forEach {
            val parts = it.split(" ")
            val opponentMove = enumValueOf<OpponentMove>(parts[0])
            val endResult = enumValueOf<EndResult>(parts[1])

            if(endResult.str == "lose") {
                playerScore += getMoveScore(getLosingPlayerMove(opponentMove).str)
            } else if(endResult.str == "draw") {
                playerScore += 3
                playerScore += getMoveScore(getDrawPlayerMove(opponentMove).str)
            } else if(endResult.str == "win") {
                playerScore += 6
                playerScore += getMoveScore(getWinningPlayerMove(opponentMove).str)
            }

        }

        return playerScore
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
