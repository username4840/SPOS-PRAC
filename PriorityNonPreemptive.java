package spos;

import java.util.*;

class PriorityProcess {
    String pid;
    int arrival, burst, priority, completion, turnaround, waiting;
    boolean done = false;

    PriorityProcess(String pid, int arrival, int burst, int priority) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
    }
}

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<PriorityProcess> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time, Burst Time, and Priority for P" + (i + 1) + ": ");
            int arrival = sc.nextInt();
            int burst = sc.nextInt();
            int priority = sc.nextInt();
            processes.add(new PriorityProcess("P" + (i + 1), arrival, burst, priority));
        }

        int time = 0;
        int completed = 0;

        while (completed < n) {
            PriorityProcess current = null;
            int minPriority = Integer.MAX_VALUE;

            // Find process with highest priority (lowest priority number)
            for (PriorityProcess p : processes) {
                if (!p.done && p.arrival <= time && p.priority < minPriority) {
                    minPriority = p.priority;
                    current = p;
                }
            }

            if (current == null) {
                time++; // No process has arrived yet
                continue;
            }

            // Execute the selected process
            time += current.burst;
            current.completion = time;
            current.turnaround = current.completion - current.arrival;
            current.waiting = current.turnaround - current.burst;
            current.done = true;
            completed++;
        }

        // Display result table
        System.out.println("\nPID\tAT\tBT\tPR\tCT\tTAT\tWT");
        for (PriorityProcess p : processes) {
            System.out.println(p.pid + "\t" + p.arrival + "\t" + p.burst + "\t" + p.priority
                    + "\t" + p.completion + "\t" + p.turnaround + "\t" + p.waiting);
        }

        // Calculate averages
        double avgTAT = processes.stream().mapToInt(p -> p.turnaround).average().orElse(0);
        double avgWT = processes.stream().mapToInt(p -> p.waiting).average().orElse(0);

        System.out.printf("\nAverage Turnaround Time: %.2f", avgTAT);
        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);

        sc.close();
    }
}
