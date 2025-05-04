package ab;

import java.util.*;

class Ring {
    static int processes[] = {1, 2, 3, 4, 5};
    static boolean active[] = {true, true, true, true, true};
    static int coordinator = 5;

    static void startElection(int initiator) {
        System.out.println("Process " + processes[initiator] + " started an election.");
        List<Integer> electionList = new ArrayList<>();

        int index = initiator;
        do {
            if (active[index]) {
                electionList.add(processes[index]);
                System.out.println("Process " + processes[index] + " added to election list.");
            }
            index = (index + 1) % processes.length;
        } while (index != initiator);

        int newCoordinator = Collections.max(electionList);
        coordinator = newCoordinator;
        System.out.println("Process " + coordinator + " becomes the coordinator.");
    }

    public static void main(String[] args) {
        // Simulate coordinator failure
        System.out.println("Current Coordinator " + coordinator + " failed.");
        active[4] = false;

        // Process 2 initiates the election
        startElection(1);
    }
}
