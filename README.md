# suspicious_ips

The main function provides a list of suspicious IPs that were used among previous IPs to test the app. Notice that these IPs are in CIDR format: x.x.x.x/x, it is assumed that
an IP MUST be given in that format.

After running the app it will prompt you to input an IP through the terminal to check if it is allowed, it is assumed that a concrete IP must be given: x.x.x.x.

Implementation:
  The implementation of the task was using a binary tree where moving right along the tree corresponds to reading the bits of the IP from left to righ'
  Moving left corresponds to a '0' and moving right corresponds to a '1'. So for example if we have the input "101" we would move from the root right then left then right again.
  
  Notice that the tree is at most of height 32 and so search in the tree is O(log(32)) which is the same as O(1).
  The initialization of the tree involves traversing the tree which is O(1) as explained above N times, for N suspicious IPs given the list. Overall the initialization is O(n).
  
  In terms of space this at most stores O(32*n) because in the worst case every bit of the 32 bits in an IP will create a new node in the tree, so O(n) overall.
  
  This solution is therefore as optimized as possible in terms of runtime and memory, I hope it's also coded well! :)
