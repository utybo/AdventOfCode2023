import kotlin.math.pow

fun d4part1(text: String): Int {
    return text.lines().sumOf { line ->
        val (expected, result) = line.split(": ")[1].split(" | ").let { it[0] to it[1] }
        val expectedSet = expected.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        val resultSet = result.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        val intersection = expectedSet.intersect(resultSet)
        val intersectionCount = intersection.count()
        if (intersectionCount == 0) 0 else 2.0.pow(intersectionCount - 1).toInt()
    }
}

fun d4part2(text: String): Int {
    val repetitionsOfLines = mutableMapOf<Int, Int>()
    return text.lines().mapIndexed { index, line ->
        val repetitionsOfThisLine = repetitionsOfLines[index] ?: 0
        val (expected, result) = line.split(": ")[1].split(" | ").let { it[0] to it[1] }
        val expectedSet = expected.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        val resultSet = result.split(" ").filter { it.isNotBlank() }.map { it.toInt() }.toSet()
        val intersection = expectedSet.intersect(resultSet)
        for (i in 0..intersection.size) {
            repetitionsOfLines.merge(index + i, 1 + repetitionsOfThisLine) { a, b -> a + b }
        }
        1 + repetitionsOfThisLine
    }.sum()
}