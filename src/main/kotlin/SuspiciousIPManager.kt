class SuspiciousIPManager(CIDRBlocks: List<String>) {

    private val suspiciousIPStore = IPSearchTree()

    init {
        for (block in CIDRBlocks) {
            validateBlock(block)
            val splitCIDR = block.split("/")
            val ipString = splitCIDR[0]
            val maskNumber = splitCIDR[1].toInt()
            var bitStringCIDR = IP(ipString).getBinaryString().substring(0, maskNumber)
            if (maskNumber < 32) {
                bitStringCIDR += '*'
            }
            suspiciousIPStore.addCIDR(bitStringCIDR)
        }
    }

    fun isAllowed(incomingIp: String): Boolean {
        val binaryIpString = IP(incomingIp).getBinaryString()
        val binaryIPSearchNode = suspiciousIPStore.searchIP(binaryIpString) ?: return true
        if (binaryIPSearchNode.subnetIsStored)
            return false
        return true
    }

    private fun validateBlock(block: String) {
        val splitCIDR = block.split("/")
        if (splitCIDR.size != 2) {
            throw IllegalArgumentException(
                "CIDR Block must consist of mask number with a \'/\' separating it" +
                        "from the rest of the IP address"
            )
        }

        val maskNumber = splitCIDR[1].toInt()
        if (maskNumber < 0 || maskNumber > 32)
            throw IllegalArgumentException("Mask number has to be between 0 and 32")

    }

}