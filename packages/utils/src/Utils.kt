import java.io.File
import java.math.BigInteger
import java.nio.file.Paths
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 *
 * TODO: resolve generally
 */
fun readInput(name: String) = File("packages/solutions/src", "$name.txt")
    .readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)