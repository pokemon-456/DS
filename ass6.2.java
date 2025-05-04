package ab;

import java.util.*;

class Bully {
    static int processes[] = {1, 2, 3, 4, 5};  // Process IDs
    static boolean active[] = {true, true, true, true, true};
    static int coordinator = 5;

    static void startElection(int initiator) {
        System.out.println("Process " + initiator + " started an election.");
        boolean foundHigher = false;
        for (int i = initiator + 1; i < processes.length; i++) {
            if (active[i]) {
                System.out.println("Process " + processes[initiator] + " sent election to Process " + processes[i]);
                foundHigher = true;
            }
        }
        if (!foundHigher) {
            coordinator = processes[initiator];
            System.out.println("Process " + coordinator + " becomes the coordinator.");
        }
    }

    public static void main(String[] args) {
        // Simulate coordinator failure
        System.out.println("Current Coordinator " + coordinator + " failed.");
        active[4] = false;  // Process 5 (index 4) failed

        // Process 2 initiates the election
        startElection(1);
    }
}
