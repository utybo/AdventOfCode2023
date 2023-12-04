import kotlin.math.max

data class Game(val id: Int, val maxRed: Int, val maxBlue: Int, val maxGreen: Int)

private val startRegex = Regex("""Game (\d+): .+""")
val criteria = mapOf(
    "red" to 12,
    "green" to 13,
    "blue" to 14
)

fun d2part1(text: String): Int {
    return text.lines().mapNotNull { line ->
        val gameId = startRegex.find(line)?.groupValues?.get(1)?.toInt() ?: return@mapNotNull null
        val roundsString = line.substringAfter(": ")
        val maxPerColor = roundsString.splitToSequence("; ")
            .flatMap { round ->
                round
                    .split(", ")
                    .map { roundElement ->
                        roundElement.split(" ").let { it[1] to it[0].toInt() }
                    }
            }
            .fold(mutableMapOf<String, Int>()) { map, elem ->
                map.merge(elem.first, elem.second) { prev, new -> max(prev, new) }
                map
            }
        Game(gameId, maxPerColor["red"] ?: 0, maxPerColor["blue"] ?: 0, maxPerColor["green"] ?: 0)
    }.filter {
        it.maxBlue <= (criteria["blue"] ?: 0) &&
                it.maxGreen <= (criteria["green"] ?: 0) &&
                it.maxRed <= (criteria["red"] ?: 0)
    }.sumOf { it.id }
}


fun d2part2(text: String): Int {
    return text.lines().mapNotNull { line ->
        val gameId = startRegex.find(line)?.groupValues?.get(1)?.toInt() ?: return@mapNotNull null
        val roundsString = line.substringAfter(": ")
        val maxPerColor = roundsString.splitToSequence("; ")
            .flatMap { round ->
                round
                    .split(", ")
                    .map { roundElement ->
                        roundElement.split(" ").let { it[1] to it[0].toInt() }
                    }
            }
            .fold(mutableMapOf<String, Int>()) { map, elem ->
                map.merge(elem.first, elem.second) { prev, new -> max(prev, new) }
                map
            }
        Game(gameId, maxPerColor["red"] ?: 0, maxPerColor["blue"] ?: 0, maxPerColor["green"] ?: 0)
    }.sumOf { it.maxBlue *  it.maxRed * it.maxGreen }
}

