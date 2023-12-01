fun day1part1(text: String): Int {
    val lineNumber = text.lines().map { line ->
        val digits = line.toList().mapNotNull { it.digitToIntOrNull() }
        digits.first() * 10 + digits.last()
    }
    return lineNumber.sum()
}

val digits = mapOf(
    "one" to 1,
    "1" to 1,
    "two" to 2,
    "2" to 2,
    "three" to 3,
    "3" to 3,
    "four" to 4,
    "4" to 4,
    "five" to 5,
    "5" to 5,
    "six" to 6,
    "6" to 6,
    "seven" to 7,
    "7" to 7,
    "eight" to 8,
    "8" to 8,
    "nine" to 9,
    "9" to 9
)

fun findFirstOf(str: String, mapping: Map<String, Int>): Pair<String, Int> {
    val firstOne = mapping.keys
        .map { search ->
            search to str.indexOf(search).takeIf { it != -1 }
        }
        .filter { it.second != null }
        .minBy { it.second!! }
    return firstOne.first to mapping.getValue(firstOne.first)
}

fun findLastOf(str: String, mapping: Map<String, Int>): Pair<String, Int> {
    val lastOne = mapping.keys
        .map { search ->
            search to str.lastIndexOf(search).takeIf { it != -1 }
        }
        .filter { it.second != null }
        .maxBy { it.second!! }
    return lastOne.first to mapping.getValue(lastOne.first)
}

fun day1part2(text: String): Int {
    val lineNumber = text.lines().map { line ->
        //val treatedLine = replaceLeftToRight(line, digits.mapValues { it.value.toString() })
        //println(treatedLine)
        //val digits = treatedLine.toList().mapNotNull { it.digitToIntOrNull() }
        val first = findFirstOf(line, digits).second
        val last = findLastOf(line, digits).second
        first * 10 + last
    }
    return lineNumber.sum()
}