package days

private const val LITERAL_PACKET = 4

private const val OFFSET_CONTENT = 6

class Day16 : Day00 {

    data class ContentInfo(val contentLength: Int, val subVersionSum: Int)

    fun parseHexInput(hex: String) =
        hex.toCharArray().map { it.toString().toInt(16).toString(2).padStart(LITERAL_PACKET, '0') }.joinToString("")

    fun bin2int(binString: String) = binString.toInt(2)

    fun getVersion(binString: String) = bin2int(binString.take(3))

    fun getType(binString: String) = bin2int(binString.substring(3, OFFSET_CONTENT))

    private fun getLiteralPacketInfo(content: String): ContentInfo {
        var takeChunk = true
        val length = content.chunked(5)
            .takeWhile { it ->
                val result = takeChunk
                if (it[0] == '0') takeChunk = false
                result
            }.sumOf { it.length }
        return ContentInfo(length, 0)
    }

    private fun subPacketInfo(type: Int, content: String) =
        when (type) {
            LITERAL_PACKET -> getLiteralPacketInfo(content)
            else /* operator */ -> getOperatorPacketInfo(content)
        }

    private fun getOperatorPacketInfo(content: String) =
        if (content[0] == '0')
            getOperatorWithLengthInfo(content)
        else
            getOperatorWithNumberInfo(content)

    private fun getOperatorWithLengthInfo(content: String): ContentInfo {
        val lengthOfPackages = content.drop(1).take(15).toInt(2)
        var packetsContent = content.drop(16).take(lengthOfPackages)

        var subVersionSum = 0
        var i = 0
        while (i < lengthOfPackages) {
            val version = getVersion(packetsContent)
            val type = getType(packetsContent)
            val subInfo = subPacketInfo(type, packetsContent.drop(OFFSET_CONTENT))
            val subLength = OFFSET_CONTENT + subInfo.contentLength
            i += subLength
            packetsContent = packetsContent.drop(subLength)
            subVersionSum += (version + subInfo.subVersionSum)
        }
        return ContentInfo(lengthOfPackages + 16, subVersionSum)
    }

    private fun getOperatorWithNumberInfo(content: String): ContentInfo {
        val numberOfPackages = content.drop(1).take(11).toInt(2)
        var packetsContent = content.drop(12)

        var packetsLength = 12
        var subVersionSum = 0
        var i = 0
        while (i < numberOfPackages) {
            val version = getVersion(packetsContent)
            val type = getType(packetsContent)
            val subInfo = subPacketInfo(type, packetsContent.drop(OFFSET_CONTENT))
            val subLength = OFFSET_CONTENT + subInfo.contentLength
            i += 1
            packetsContent = packetsContent.drop(subLength)
            packetsLength += subLength
            subVersionSum += (version + subInfo.subVersionSum)
        }
        return ContentInfo(packetsLength, subVersionSum)
    }

    override fun analyse(lines: List<String>): Any {
        val binString = parseHexInput(lines[0])
        val version = getVersion(binString)
        val type = getType(binString)
        val info = subPacketInfo(type, binString.substring(OFFSET_CONTENT))
        return version + info.subVersionSum
    }

    override fun analyse2(lines: List<String>): Any {
        TODO("Not yet implemented")
    }

}
