package ab;

class TokenRing {
    private static int n = 5; // Number of processes
    private static int tokenHolder = 0; // Initially, process 0 has the token

    // Method to simulate a process entering and leaving the critical section
    public synchronized void enterCriticalSection(int processId) {
        // Wait until the process has the token
        while (tokenHolder != processId) {
            try {
                wait(); // Wait until the process has the token
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Enter critical section
        System.out.println("Process " + processId + " is in critical section");

        // Simulate the process working in the critical section
        try {
            Thread.sleep(1000); // Simulate the critical section work
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Leaving the critical section
        System.out.println("Process " + processId + " is leaving critical section");

        // Pass the token to the next process
        tokenHolder = (tokenHolder + 1) % n; // Token goes to the next process
        notifyAll(); // Notify all processes that token has been passed
    }

    public static void main(String[] args) {
        TokenRing tokenRing = new TokenRing();

        // Create and start threads for each process
        for (int i = 0; i < n; i++) {
            final int processId = i;
            new Thread(() -> {
                while (true) {
                    tokenRing.enterCriticalSection(processId);
                    try {
                        Thread.sleep(1000); // Random delay between entering critical section
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }).start();
        }
    }
}
