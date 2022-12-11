package aoc2022.days

import aoc.days.DayXX

data class TreeFile(val name: String, val size: Long)

class TreeNode(val parent: TreeNode?, val name: String) {

	private val files = mutableListOf<TreeFile>()
	private val subs = mutableListOf<TreeNode>()

	fun addDir(sub: TreeNode) {
		subs.add(sub)
	}

	fun addFile(file: TreeFile) {
		files.add(file)
	}

	fun getDir(name: String) = subs.find { it.name == name }

	fun getAllDirs(): List<TreeNode> =
		subs + subs.flatMap { it.getAllDirs() } + if (parent == null) listOf(this) else emptyList()

	fun size(): Long = files.map { it.size }.sum() + subs.map { it.size() }.sum()
}

class Day07 : DayXX {

	fun addNodeContent(node: TreeNode, lines: List<String>): List<String> {
		val content = lines.takeWhile { !it.startsWith("$") }
		content.forEach {
			val parts = it.split(" ")
			if (it.startsWith("dir"))
				node.addDir(TreeNode(node, parts[1]))
			else
				node.addFile(TreeFile(parts[1], parts[0].toLong()))
		}
		return lines.drop(content.size)
	}

	private fun parseTree(node: TreeNode, lines: List<String>): TreeNode {
		if (lines.isEmpty()) return node

		val next = lines[0]

		if (next == "$ cd ..") return parseTree(node.parent!!, lines.drop(1))

		if (next.startsWith("$ cd")) return parseTree(node.getDir(next.split(" ")[2])!!, lines.drop(1))

		val remaining = addNodeContent(node, lines.drop(1))
		return parseTree(node, remaining)
	}

	override fun analyse(lines: List<String>): Any {
		val root = TreeNode(null, "/")
		parseTree(root, lines.drop(1))


		return filterByMaxSize(root, 100_000L).map { it.size() }.sum()
	}

	private fun filterByMaxSize(node: TreeNode, size: Long) = node.getAllDirs().filter { it.size() <= size }

	private fun filterByMinSize(node: TreeNode, size: Long) = node.getAllDirs().filter { it.size() >= size }

	override fun analyse2(lines: List<String>): Any {
		val root = TreeNode(null, "/")
		parseTree(root, lines.drop(1))
		val unused = 70_000_000L - root.size()
		val remaining = 30_000_000L - unused

		return filterByMinSize(root, remaining).map { it.size() }.sorted().first()
	}

}
