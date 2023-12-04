data class Point(val row: Int, val column: Int) {
    fun isValid(rowTotal: Int, columnTotal: Int): Boolean {
        return row in 0..<rowTotal && column in 0..<columnTotal
    }
}

fun d3part1(text: String): Int {
    val lines = text.lines()
    var total = 0
    for (lineNb in lines.indices) {
        var columnNb = 0
        val currentLine = lines[lineNb]
        while (columnNb < currentLine.length) {
            if (currentLine[columnNb].isDigit()) {
                val number = currentLine.substring(columnNb).takeWhile { it.isDigit() }
                val surroundingIndices = sequence {
                    // OOOOOOOO
                    // OxxxxxxO
                    // OOOOOOOO
                    yield(Point(lineNb, columnNb - 1))
                    for (rowOffset in -1..number.length) {
                        yield(Point(lineNb - 1, columnNb + rowOffset))
                        yield(Point(lineNb + 1, columnNb + rowOffset))
                    }
                    yield(Point(lineNb, columnNb + number.length))
                }
                val hasSymbolInSurrounding = surroundingIndices
                    .filter {
                        it.isValid(lines.size, currentLine.length)
                    }.map { (row, column) ->
                        lines[row][column]
                    }.filter {
                        !it.isDigit() && it != '.'
                    }.any()
                if (hasSymbolInSurrounding) {
                    total += number.toInt()
                }
                columnNb += number.length
            } else {
                columnNb++
                continue
            }
        }
    }
    return total
}

fun d3part2(text: String): Int {
    val lines = text.lines()
    var total = 0
    for (lineNb in lines.indices) {
        var columnNb = 0
        val currentLine = lines[lineNb]
        while (columnNb < currentLine.length) {
            if (currentLine[columnNb] == '*') {
                val surroundingIndices = sequence {
                    for (lineIndex in listOf(lineNb - 1, lineNb + 1)) {
                        val surroundingRow = lines[lineIndex].substring(columnNb - 1, columnNb + 2)
                        if (surroundingRow.matches(Regex("""\d\.\d"""))) {
                            // There are 2 numbers here, add them separately
                            yield(Point(lineIndex, columnNb - 1))
                            yield(Point(lineIndex, columnNb + 1))
                        } else {
                            // Otherwise just add the first one that comes up
                            surroundingRow.indexOfFirst { it.isDigit() }.takeIf { it != -1 }?.let {
                                yield(Point(lineIndex, columnNb - 1 + it))
                            }
                        }
                    }
                    yield(Point(lineNb, columnNb - 1))
                    yield(Point(lineNb, columnNb + 1))
                }
                val surroundingNumbers = surroundingIndices.mapNotNull { (row, column) ->
                    if (!lines[row][column].isDigit()) {
                        null
                    } else {
                        lines[row].takeAround(column) { it.isDigit() }.toInt()
                    }
                }.toList()
                if (surroundingNumbers.size == 2) {
                    total += surroundingNumbers[0] * surroundingNumbers[1]
                }
            }
            columnNb++
        }
    }
    return total
}

inline fun String.takeAround(startingAt: Int, predicate: (Char) -> Boolean): String {
    var firstIndex = startingAt
    var lastIndex = startingAt
    for (index in startingAt downTo -1) {
        if (index == -1 || !predicate(get(index))) {
            firstIndex = index
            break
        }
    }
    for (index in startingAt..length) {
        if (index == length || !predicate(get(index))) {
            lastIndex = index
            break
        }
    }
    return if (firstIndex + 1 < lastIndex)
        substring(firstIndex + 1, lastIndex)
    else
        ""
}