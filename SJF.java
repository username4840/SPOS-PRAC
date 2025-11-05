package spos;

import java.util.*;

class SJFPProcess {
    String pid;
    int arrival, burst, remaining, completion, turnaround, waiting;

    SJFPProcess(String pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remaining = burst;
    }
}

public class SJF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<SJFPProcess> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for P" + (i + 1) + ": ");
            int arrival = sc.nextInt();
            int burst = sc.nextInt();
            processes.add(new SJFPProcess("P" + (i + 1), arrival, burst));
        }

        int complete = 0, time = 0;
        SJFPProcess current = null;

        while (complete < n) {
            // Find the process with the minimum remaining time among arrived ones
            SJFPProcess shortest = null;
            int minRemaining = Integer.MAX_VALUE;

            for (SJFPProcess p : processes) {
                if (p.arrival <= time && p.remaining > 0 && p.remaining < minRemaining) {
                    shortest = p;
                    minRemaining = p.remaining;
                }
            }

            if (shortest == null) {
                time++;
                continue;
            }

            // Run the chosen process for one time unit
            shortest.remaining--;
            time++;

            // If the process completes
            if (shortest.remaining == 0) {
                complete++;
                shortest.completion = time;
                shortest.turnaround = shortest.completion - shortest.arrival;
                shortest.waiting = shortest.turnaround - shortest.burst;
            }
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (SJFPProcess p : processes) {
            System.out.println(p.pid + "\t" + p.arrival + "\t" + p.burst + "\t"
                    + p.completion + "\t" + p.turnaround + "\t" + p.waiting);
        }

        double avgTAT = processes.stream().mapToInt(p -> p.turnaround).average().orElse(0);
        double avgWT = processes.stream().mapToInt(p -> p.waiting).average().orElse(0);

        System.out.printf("\nAverage Turnaround Time: %.2f", avgTAT);
        System.out.printf("\nAverage Waiting Time: %.2f\n", avgWT);

        sc.close();
    }
}
