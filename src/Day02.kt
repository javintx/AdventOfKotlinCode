import java.io.File
import java.io.PrintStream
import java.nio.file.Files
import java.nio.file.Paths

interface DiveParser {
    fun parse(command: String)
    fun printResultTo(out: PrintStream)
}

private open class DiveParserOne : DiveParser {
    protected var forward: Long = 0
    protected var depth: Long = 0
    override fun parse(command: String) {
        val order = command.split(" ").toTypedArray()
        when (order[0]) {
            "forward" -> forward += order[1].toLong()
            "down" -> depth += order[1].toLong()
            "up" -> depth -= order[1].toLong()
            else -> System.err.println("Order not implemented")
        }
    }

    override fun printResultTo(out: PrintStream) {
        out.printf("Result: %d%n", forward * depth)
    }
}

private class DiveParserTwo : DiveParserOne() {
    private var aim: Long = 0
    override fun parse(command: String) {
        val order = command.split(" ").toTypedArray()
        when (order[0]) {
            "forward" -> {
                forward += order[1].toLong()
                depth += order[1].toLong() * aim
            }
            "down" -> aim += order[1].toLong()
            "up" -> aim -= order[1].toLong()
            else -> System.err.println("Order not implemented")
        }
    }
}

class Day02 {
    fun solve(commandsFile: String, diveParser: DiveParser) {
        Files.lines(Paths.get(File("src", commandsFile).toURI())).use { commands ->
            commands.forEach { command: String -> diveParser.parse(command) }
            diveParser.printResultTo(System.out)
        }
    }
}

fun main() {
    val dive = Day02()
    dive.solve("Day02_test.txt", DiveParserOne())
    dive.solve("Day02.txt", DiveParserOne())

    dive.solve("Day02_test.txt", DiveParserTwo())
    dive.solve("Day02.txt", DiveParserTwo())
}
