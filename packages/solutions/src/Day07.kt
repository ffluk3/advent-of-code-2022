class File {
    private val name: String
    private val size: Int

    constructor(name: String, size: Int) {
        this.name = name
        this.size = size
    }

    fun getSize(): Int {
        return this.size
    }
}

class Directory {
    private val name: String
    private val parent: Directory?
    private val files = mutableListOf<File>()
    var size: Int
    private val directories = mutableMapOf<String, Directory>()

    constructor(name: String, parent: Directory?) {
        this.parent = parent
        this.name = name
        this.size = 0
    }

    fun getName(): String {
        return name
    }

    fun addFile(file: File) {
        files.add(file)

        var pointer: Directory? = this

        while (pointer != null) {
            pointer.size += file.getSize()
            pointer = pointer.getParent()
        }
    }

    fun addDirectory(directory: Directory) {
        directories[directory.getName()] = directory
    }

    fun getChildDirectory(name: String): Directory {
        return directories[name]!!
    }

    fun getChildDirectories(): Set<Directory> {
        return directories.values.toSet()
    }

    fun getParent(): Directory? {
        return parent
    }

    override fun toString(): String {
        return "${name}: $size"
    }
}

class FileSystem {
    private val rootDirectory: Directory = Directory("/", null)
    private var currentPointer: Directory

    init {
        currentPointer = rootDirectory
    }

    fun changeDirectory(path: String) {
        currentPointer = when (path) {
            "/" -> rootDirectory
            ".." -> currentPointer.getParent()!!
            else -> currentPointer.getChildDirectory(path)
        }
    }

    fun addDirectory(name: String) {
        currentPointer.addDirectory(Directory(name, currentPointer))
    }

    fun addFile(file: File) {
        currentPointer.addFile(file)
    }

    fun getDirectorySizes(): List<Int> {
        var res = mutableListOf<Int>()
        val directoriesToAdd = ArrayDeque<Directory>()
        directoriesToAdd.add(rootDirectory)

        val accountedForDirs = mutableListOf<String>()

        while (directoriesToAdd.size > 0) {
            val dir = directoriesToAdd.removeFirst()
            res.add(dir.size)
            directoriesToAdd.addAll(dir.getChildDirectories())
            accountedForDirs.add(dir.getName())
        }

        return res
    }

    fun currentDirectory(): String {
        return currentPointer.getName()
    }
}


fun main() {

    fun part1(input: List<String>): Int {
        val fileSystem = FileSystem()

        var i = 0
        while (i < input.size) {
            val command = input[i]
            print("Reading ${command}, curr ${fileSystem.currentDirectory()}")

            if (command.contains("cd")) {

                val regex = Regex("\\\$ cd (.*)")
                val result = regex.find(command)
                val (targetDir) = result!!.destructured
                println(" changing directory $targetDir")
                fileSystem.changeDirectory(targetDir)
                i++
            } else if (command.contains("ls")) {
                println(" scanning folder")
                // ls use case
                i++
                while (i < input.size && !input[i].contains("$")) {
                    print("Reading ${input[i]} curr: ${fileSystem.currentDirectory()}")
                    val parts = input[i].split(" ")
                    if (parts[0] == "dir") {
                        println(" add dir ${parts[1]}")
                        fileSystem.addDirectory(parts[1])
                    } else {
                        println(" add file ${parts[1]}")

                        fileSystem.addFile(File(parts[1], parts[0].toInt()))
                    }
                    i++
                }
            } else {
                i++
            }
        }

        val sizes = fileSystem.getDirectorySizes()
        sizes.println()

        val sumUnder = sizes.filter {
            it <= 100000
        }.reduce { acc, next ->
            acc + next
        }

        return sumUnder
    }

    fun part2(input: List<String>): Int {
        val fileSystem = FileSystem()

        var i = 0
        while (i < input.size) {
            val command = input[i]
            print("Reading ${command}, curr ${fileSystem.currentDirectory()}")

            if (command.contains("cd")) {

                val regex = Regex("\\\$ cd (.*)")
                val result = regex.find(command)
                val (targetDir) = result!!.destructured
                println(" changing directory $targetDir")
                fileSystem.changeDirectory(targetDir)
                i++
            } else if (command.contains("ls")) {
                println(" scanning folder")
                // ls use case
                i++
                while (i < input.size && !input[i].contains("$")) {
                    print("Reading ${input[i]} curr: ${fileSystem.currentDirectory()}")
                    val parts = input[i].split(" ")
                    if (parts[0] == "dir") {
                        println(" add dir ${parts[1]}")
                        fileSystem.addDirectory(parts[1])
                    } else {
                        println(" add file ${parts[1]}")

                        fileSystem.addFile(File(parts[1], parts[0].toInt()))
                    }
                    i++
                }
            } else {
                i++
            }
        }

        val sizes = fileSystem.getDirectorySizes().sorted()

        val totalSize = sizes[sizes.size - 1]

        val usedSpace = 70000000 - totalSize

        usedSpace.println()

        sizes.forEach {
            if(usedSpace + it >= 30000000) {
                return it
            }
        }

        throw Error("Couldn't find size")
    }

    runAdventOfCodeSuite("Day07", ::part1, 95437, ::part2, 24933642)

}
