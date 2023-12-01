import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun main(args: Array<String>) {
    val text = Path.of("inputs", "d1.txt").readText()
    println(day1part1(text))
    println(day1part2(text))
}