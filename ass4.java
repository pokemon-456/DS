import java.util.*;

public class BerkeleyAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Number of slave processes
        System.out.print("Enter number of slave processes: ");
        int n = sc.nextInt();

        // Clock times (in milliseconds or seconds) for master and slaves
        int[] clocks = new int[n + 1]; // index 0 = master

        System.out.print("Enter time of master process: ");
        clocks[0] = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter time of slave process " + i + ": ");
            clocks[i] = sc.nextInt();
        }

        System.out.println("\nTime values before synchronization:");
        for (int i = 0; i <= n; i++) {
            System.out.println((i == 0 ? "Master" : "Slave " + i) + ": " + clocks[i]);
        }

        // Calculate time differences
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += clocks[i] - clocks[0];
        }

        int avgDiff = sum / (n + 1); // average adjustment

        System.out.println("\nAverage time difference: " + avgDiff);

        // Synchronize clocks
        clocks[0] += avgDiff;
        for (int i = 1; i <= n; i++) {
            clocks[i] -= (clocks[i] - clocks[0] - avgDiff);
        }

        System.out.println("\nTime values after synchronization:");
        for (int i = 0; i <= n; i++) {
            System.out.println((i == 0 ? "Master" : "Slave " + i) + ": " + clocks[i]);
        }
    }
}
