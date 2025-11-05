package Scheduling;
import java.util.ArrayList; 
import java.util.Comparator; 
import java.util.List; 
import java.util.Scanner;
public class SchedulingAlgorithms {
	static class Process {
		int id;
		int burstTime; int arrivalTime; int priority;
		int remainingTime; int waitingTime;
		int turnaroundTime; int completionTime;
		public Process(int id, int burstTime, int arrivalTime) { this.id = id;
		this.burstTime = burstTime; this.arrivalTime = arrivalTime; this.remainingTime = burstTime;
		}
		public Process(int id, int burstTime, int priority, boolean isPriority) { this.id = id;
		this.burstTime = burstTime; this.priority = priority; this.arrivalTime = 0; this.remainingTime = burstTime;
		 
		}

		public Process(int id, int burstTime) { this(id, burstTime, 0);
		}
		}
		public static void main(String[] args) { Scanner scanner = new Scanner(System.in); while (true) {
		System.out.println("\nCPU Scheduling Algorithms Menu:"); System.out.println("1. First-Come, First-Served (FCFS)"); System.out.println("2. Shortest Job First (SJF) - Preemptive (SRTF)"); System.out.println("3. Priority - Non-Preemptive"); System.out.println("4. Round Robin (RR)");
		System.out.println("5. Exit"); System.out.print("Enter your choice: "); int choice = scanner.nextInt();
		switch (choice) {
		case 1: runFCFS(scanner); break;
		case 2: runSJFPreemptive(scanner); break;
		case 3: runPriorityNonPreemptive(scanner); break; case 4: runRoundRobin(scanner); break;
		case 5:
		System.out.println("Exiting..."); scanner.close();
		return; default:
		System.out.println("Invalid choice. Please try again.");
		}
		}
		}
		public static void runFCFS(Scanner sc) { System.out.print("Enter the number of processes: "); int n = sc.nextInt();
		List<Process> processes = new ArrayList<>(); for (int i = 0; i < n; i++) {
		System.out.print("Enter Burst Time for Process " + (i + 1) + ": "); processes.add(new Process(i + 1, sc.nextInt()));
		}
		int currentTime = 0;
		 
		for (Process p : processes) { p.waitingTime = currentTime; currentTime += p.burstTime; p.turnaroundTime = currentTime;
		}
		printResults(processes, false, false);
		}

		public static void runSJFPreemptive(Scanner sc) { System.out.print("Enter the number of processes: "); int n = sc.nextInt();
		List<Process> processes = new ArrayList<>(); for (int i = 0; i < n; i++) {
		System.out.print("Enter Arrival Time for Process " + (i + 1) + ": "); int at = sc.nextInt();
		System.out.print("Enter Burst Time for Process " + (i + 1) + ": "); int bt = sc.nextInt();
		processes.add(new Process(i + 1, bt, at));
		}
		int currentTime = 0; int completed = 0;
		while (completed < n) { Process shortestJob = null;
		int minRemTime = Integer.MAX_VALUE;
		for (Process p : processes) {
		if (p.arrivalTime <= currentTime && p.remainingTime > 0 && p.remainingTime < minRemTime) {
		minRemTime = p.remainingTime; shortestJob = p;
		}
		}
		if (shortestJob == null) { currentTime++;
		} else {
		shortestJob.remainingTime--; currentTime++;
		if (shortestJob.remainingTime == 0) { shortestJob.completionTime = currentTime; shortestJob.turnaroundTime = shortestJob.completionTime -
		shortestJob.arrivalTime;
		 
		shortestJob.waitingTime = shortestJob.turnaroundTime - shortestJob.burstTime; completed++;
		}
		}
		}
		printResults(processes, true, false);
		}

		public static void runPriorityNonPreemptive(Scanner sc) { System.out.print("Enter the number of processes: "); int n = sc.nextInt();
		List<Process> processes = new ArrayList<>(); for (int i = 0; i < n; i++) {
		System.out.print("Enter Burst Time for Process " + (i + 1) + ": "); int bt = sc.nextInt();
		System.out.print("Enter Priority for Process " + (i + 1) + " (lower number = higher priority): ");
		int priority = sc.nextInt();
		processes.add(new Process(i + 1, bt, priority, true));
		}
		processes.sort(Comparator.comparingInt(p -> p.priority)); int currentTime = 0;
		for (Process p : processes) {
		p.waitingTime = currentTime; currentTime += p.burstTime; p.turnaroundTime = currentTime;
		}
		printResults(processes, false, true);
		}

		public static void runRoundRobin(Scanner sc) { System.out.print("Enter the number of processes: "); int n = sc.nextInt();
		List<Process> processes = new ArrayList<>(); for (int i = 0; i < n; i++) {
		System.out.print("Enter Burst Time for Process " + (i + 1) + ": "); processes.add(new Process(i + 1, sc.nextInt()));
		}
		System.out.print("Enter Time Quantum: "); int quantum = sc.nextInt();
		 
		int currentTime = 0;
		while (processes.stream().anyMatch(p -> p.remainingTime > 0)) { for (Process p : processes) {
		if (p.remainingTime > 0) {
		int executeTime = Math.min(p.remainingTime, quantum); currentTime += executeTime;
		p.remainingTime -= executeTime; if (p.remainingTime == 0) {
		p.waitingTime = currentTime - p.burstTime;
		}
		}
		}
		}
		for (Process p : processes) {
		p.turnaroundTime = p.burstTime + p.waitingTime;
		}
		printResults(processes, false, false);
		}
		public static void printResults(List<Process> processes, boolean showArrival, boolean showPriority) {
		double totalWaitingTime = 0; double totalTurnaroundTime = 0;
		processes.sort(Comparator.comparingInt(p -> p.id));

		 

	
		 
		System.out.println("\n------------------------------------------");
		System.out.print("Process\t\tBurst Time");
		if (showArrival) System.out.print("\tArrival Time"); if (showPriority) System.out.print("\tPriority");
		System.out.println("\tWaiting Time\tTurnaround Time");
		System.out.println("	");

		for (Process p : processes) { totalWaitingTime += p.waitingTime;
		totalTurnaroundTime += p.turnaroundTime; System.out.print("P" + p.id + "\t\t" + p.burstTime);
		if (showArrival) System.out.print("\t\t" + p.arrivalTime); if (showPriority) System.out.print("\t\t" + p.priority);
		System.out.println("\t\t" + p.waitingTime + "\t\t" + p.turnaroundTime);
		 
		}
		 
		System.out.println("	");
		System.out.printf("Average Waiting Time: %.2f\n", totalWaitingTime / processes.size());
		System.out.printf("Average Turnaround Time: %.2f\n", totalTurnaroundTime / processes.size());
		}
		}
