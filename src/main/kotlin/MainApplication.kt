fun main() {
    val suspiciousIPs = listOf(
        "192.168.0.0/20",
        "192.255.255.0/28",
        "255.255.255.255/32",
        "1.1.1.1/30",
    )

    val suspiciousIPManager = SuspiciousIPManager(suspiciousIPs)
    while (true) {
        println("Please enter an IP to check:\n")
        val inputIp = readLine() ?: println("Please enter a correctly formatted IP.\n")
        println(suspiciousIPManager.isAllowed(inputIp as String))
    }
}