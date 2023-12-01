import kotlin.test.Test
import kotlin.test.assertEquals

class D1Test {
    @Test
    fun part1() {
        val text = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()
        val sum = day1part1(text)
        assertEquals(142, sum)
    }

    @Test
    fun part2() {
        val text = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()
        val sum = day1part2(text)
        assertEquals(281, sum)
    }
}