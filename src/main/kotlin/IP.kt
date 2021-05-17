class IP(
    val ipString: String
) {

    private var binaryIpString = ""
    private final val bitsInPart = 8

    init {
        binaryIpString = ipStringToBinary()
    }

    fun getBinaryString(): String {
        return binaryIpString
    }

    private fun ipStringToBinary(): String = ipString.split(".").map { ipPart ->
        Integer.toBinaryString(ipPart.toInt()).padStart(bitsInPart, '0')
    }.reduce { acc, string -> acc + string }

}