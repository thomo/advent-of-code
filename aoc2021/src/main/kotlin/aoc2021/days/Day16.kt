package aoc2021.days

import aoc.days.DayXX

private const val LITERAL_PACKET = 4

private const val OFFSET_CONTENT = 6

class Day16 : DayXX {

	data class ContentInfo(val contentLength: Int, val subVersionSum: Int, val value: Long)

	fun parseHexInput(hex: String) =
		hex.toCharArray().map { it.toString().toInt(16).toString(2).padStart(LITERAL_PACKET, '0') }.joinToString("")

	fun bin2int(binString: String) = binString.toInt(2)

	fun getVersion(binString: String) = bin2int(binString.take(3))

	fun getType(binString: String) = bin2int(binString.substring(3, OFFSET_CONTENT))

	fun getLiteralPacketInfo(content: String): ContentInfo {
		var takeChunk = true
		val subs = content.chunked(5)
			.takeWhile { it ->
				val result = takeChunk
				if (it[0] == '0') takeChunk = false
				result
			}
		val length = subs.sumOf { it.length }
		val value = subs.map { it.drop(1) }.joinToString("").toLong(2)
		return ContentInfo(length, 0, value)
	}

	fun subPacketInfo(packetsContent: String): ContentInfo {
		val version = getVersion(packetsContent)
		val type = getType(packetsContent)

		val subInfo = when (type) {
			LITERAL_PACKET -> getLiteralPacketInfo(packetsContent.drop(OFFSET_CONTENT))
			else /* operator */ -> getOperatorPacketInfo(type, packetsContent.drop(OFFSET_CONTENT))
		}

		return ContentInfo(OFFSET_CONTENT + subInfo.contentLength, version + subInfo.subVersionSum, subInfo.value)
	}

	private fun getOperatorPacketInfo(type: Int, content: String) =
		if (content[0] == '0')
			getOperatorWithLengthInfo(type, content)
		else
			getOperatorWithNumberInfo(type, content)

	private fun getOperatorWithLengthInfo(type: Int, content: String): ContentInfo {
		val lengthOfPackages = content.drop(1).take(15).toInt(2)
		var packetsContent = content.drop(16).take(lengthOfPackages)

		val subValues = mutableListOf<Long>()
		var subVersionSum = 0
		var i = 0
		while (i < lengthOfPackages) {
			val subInfo = subPacketInfo(packetsContent)

			packetsContent = packetsContent.drop(subInfo.contentLength)
			subVersionSum += subInfo.subVersionSum
			subValues.add(subInfo.value)

			i += subInfo.contentLength
		}

		return ContentInfo(16 + lengthOfPackages, subVersionSum, evalSubValues(type, subValues))
	}

	private fun evalSubValues(type: Int, subValues: MutableList<Long>) = when (type) {
		0 -> subValues.sum()
		1 -> subValues.fold(1L) { acc, i -> acc * i }
		2 -> subValues.minOf { it }
		3 -> subValues.maxOf { it }
		5 -> if (subValues[0] > subValues[1]) 1 else 0
		6 -> if (subValues[0] < subValues[1]) 1 else 0
		7 -> if (subValues[0] == subValues[1]) 1 else 0
		else -> 0
	}

	private fun getOperatorWithNumberInfo(type: Int, content: String): ContentInfo {
		val numberOfPackages = content.drop(1).take(11).toInt(2)
		var packetsContent = content.drop(12)

		val subValues = mutableListOf<Long>()
		var packetsLength = 0
		var subVersionSum = 0
		var i = 0
		while (i < numberOfPackages) {
			val subInfo = subPacketInfo(packetsContent)
			val subLength = subInfo.contentLength
			packetsContent = packetsContent.drop(subLength)
			packetsLength += subLength
			subVersionSum += subInfo.subVersionSum
			subValues.add(subInfo.value)

			i += 1
		}
		return ContentInfo(12 + packetsLength, subVersionSum, evalSubValues(type, subValues))
	}

	override fun analyse(lines: List<String>): Any {
		val binString = parseHexInput(lines[0])
		val info = subPacketInfo(binString)
		return info.subVersionSum
	}

	override fun analyse2(lines: List<String>): Any {
		val binString = parseHexInput(lines[0])
		val info = subPacketInfo(binString)
		return info.value
	}

}
