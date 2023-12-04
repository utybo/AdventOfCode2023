import kotlin.test.Test
import kotlin.test.assertEquals

class D3Test {
    @Test
    fun part1() {
        val sampleData = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent()
        assertEquals(4361, d3part1(sampleData))

    }

    @Test
    fun part2() {

        val sampleData = """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent()
        assertEquals(467835, d3part2(sampleData))
    }
}