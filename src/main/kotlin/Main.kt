import java.nio.file.Path
import kotlin.io.path.readLines
import kotlin.io.path.readText

fun main(args: Array<String>) {
    val text = Path.of("inputs", "d1.txt").readText()
    println(day1part1(text))
    println(day1part2(text))
    val d2text = Path.of("inputs", "d2.txt").readText()
    println(d2part1(d2text))
    println(d2part2(d2text))
    val d3text = Path.of("inputs", "d3.txt").readText()
    println(d3part1(d3text))
    println(d3part2(d3text))
    val d4text = Path.of("inputs", "d4.txt").readText()
    println(d4part1(d4text))
    println(d4part2(d4text))
}