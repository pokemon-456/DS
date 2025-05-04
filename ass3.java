import mpi.*;
public class DistributedSum {
    public static void main(String[] args) throws MPIException {
        MPI.Init(args); // Initialize MPI
        int rank = MPI.COMM_WORLD.Rank(); // Get current process ID
        int size = MPI.COMM_WORLD.Size(); // Get total number of processes

        int[] array = {1,2,3,4,5,6,7,8,9,10}; // Data array
        int n = array.length;
        int local_n = n / size; // Elements per processor
        int remainder = n % size; // Leftover elements

        // Calculate size of local array (some processes get an extra element if remainder > 0)
        int[] local_array = new int[local_n + (rank < remainder ? 1 : 0)];

        // Calculate starting offset for this process
        int offset = rank * local_n + Math.min(rank, remainder);
        for (int i = 0; i < local_array.length; i++) {
            local_array[i] = array[offset + i]; // Fill local array
        }

        // Compute local sum
        int local_sum = 0;
        for (int val : local_array) {
            local_sum += val;
        }

        // Gather local sums from all processes into an array
        int[] global_sums = new int[size];
        MPI.COMM_WORLD.Allgather(new int[]{local_sum}, 0, 1, MPI.INT, global_sums, 0, 1, MPI.INT);

        // Master process (rank 0) prints all intermediate and total sums
        if (rank == 0) {
            System.out.println("Number of Processes Entered: " + size);
            System.out.println("\nIntermediate Sums:");
            int total_sum = 0;
            for (int i = 0; i < size; i++) {
                System.out.println("Process " + i + ": " + global_sums[i]);
                total_sum += global_sums[i];
            }
            System.out.println("\nTotal Sum: " + total_sum);
        }

        MPI.Finalize(); // Shutdown MPI
    }
}

