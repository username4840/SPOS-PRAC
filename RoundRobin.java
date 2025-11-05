package spos;


import java.util.*;

class RRProcess {
    String pid;
    int arrival, burst, remaining, completion, turnaround, waiting;

    RRProcess(String pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remaining = burst;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter Time Quantum: ");
        int quantum = sc.nextInt();

        List<RRProcess> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for P" + (i + 1) + ": ");
            processes.add(new RRProcess("P" + (i + 1), sc.nextInt(), sc.nextInt()));
        }

        Queue<RRProcess> queue = new LinkedList<>();
        int time = 0, completed = 0;

        while (completed < n) {
            for (RRProcess p : processes)
                if (p.arrival <= time && p.remaining > 0 && !queue.contains(p))
                    queue.add(p);

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            RRProcess current = queue.poll();

            int exec = Math.min(quantum, current.remaining);
            current.remaining -= exec;
            time += exec;

            for (RRProcess p : processes)
                if (p.arrival <= time && p.remaining > 0 && !queue.contains(p))
                    queue.add(p);

            if (current.remaining == 0) {
                completed++;
                current.completion = time;
                current.turnaround = current.completion - current.arrival;
                current.waiting = current.turnaround - current.burst;
            } else {
                queue.add(current);
            }
        }

        System.out.println("\nPID\tAT\tBT\tCT\tTAT\tWT");
        for (RRProcess p : processes)
            System.out.println(p.pid + "\t" + p.arrival + "\t" + p.burst + "\t" + p.completion + "\t" + p.turnaround + "\t" + p.waiting);
    }
}
