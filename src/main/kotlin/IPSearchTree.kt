class IPSearchTree() {
    private var root: BinaryIPSearchNode? = null

    fun searchIP(binaryIPString: String): BinaryIPSearchNode? {
        validateBinaryIPString(binaryIPString)

        return searchIPHelper(binaryIPString, root)
    }

    private fun searchIPHelper(binaryIPString: String, currNode: BinaryIPSearchNode?): BinaryIPSearchNode? {
        if (currNode == null) {
            return null
        }

        if (currNode.subnetIsStored) {
            return currNode
        }

        val currBit = binaryIPString.firstOrNull() ?: return currNode
        val binaryIPStringWithoutFirstChar = binaryIPString.drop(1)
        return when (currBit) {
            '1' -> {
                searchIPHelper(binaryIPStringWithoutFirstChar, currNode.right)
            }
            '0' -> {
                searchIPHelper(binaryIPStringWithoutFirstChar, currNode.left)
            }
            else -> {
                throw java.lang.IllegalArgumentException(
                    "Search IP must be supplied the BINARY IP String, found a character" +
                            "which is not 0 or 1 or *"
                )
            }
        }
    }

    fun addCIDR(binaryIPString: String) {
        validateBinaryCIDRString(binaryIPString)
        if (root == null)
            root = BinaryIPSearchNode()

        addCIDRHelper(binaryIPString, root!!)
    }

    private fun addCIDRHelper(binaryIPString: String, currNode: BinaryIPSearchNode) {
        val currBit = binaryIPString.firstOrNull()
        if (currBit == null || currBit == '*')
            currNode.subnetIsStored = true
        else {
            val binaryIPStringWithoutFirstChar = binaryIPString.drop(1)
            when (currBit) {
                '1' -> {
                    if (currNode.right == null)
                        currNode.right = BinaryIPSearchNode()
                    addCIDRHelper(binaryIPStringWithoutFirstChar, currNode.right!!)
                }
                '0' -> {
                    if (currNode.left == null)
                        currNode.left = BinaryIPSearchNode()
                    addCIDRHelper(binaryIPStringWithoutFirstChar, currNode.left!!)
                }
                else -> {
                    throw java.lang.IllegalArgumentException(
                        "Add suspicious IP must be supplied the BINARY IP String, found a character" +
                                "which is not 0 or 1 or *"
                    )
                }
            }
        }
    }

    private fun validateBinaryIPString(binaryIPString: String) {
        if (binaryIPString.length != 32) {
            throw IllegalArgumentException("Invalid IP: [%s]".format(binaryIPString))
        }
    }

    private fun validateBinaryCIDRString(binaryIPString: String) {
        if ((binaryIPString.length < 32 && binaryIPString[binaryIPString.length - 1] != '*') || binaryIPString.length > 32
        ) {
            throw IllegalArgumentException("Invalid CIDR IP: [%s]".format(binaryIPString))
        }
    }

}