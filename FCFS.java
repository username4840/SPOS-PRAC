package process;

import java.util.Scanner;

public class FCFS 
{
	public static void main (String[] args)
	{
		Scanner sc = new  Scanner (System.in);
		
		//input number of processes
		System.out.print("enter number of processes:");
		int n = sc.nextInt();
		
		int[] processID = new int [n];
		int[] arrivalTime = new int[n];
		int[] burstTime = new int [n];
		int[] completionTime = new int[n];
		int[] turnaroundTime = new int[n];
		int[] waitingTime = new int[n];
		
		// input process details 
		for (int i=0; i<n; i++)
		{
			processID[i]= i+1;
			System.out.print("enter arrival rime for P"+ processID[i] +": ");
			arrivalTime[i]=sc.nextInt();
			System.out.print("enter burst Time for P" + processID[i] +": ");
			burstTime[i]=sc.nextInt();
			
		}
		//sortt processes by arrival time (fcfs works in arrival 
		
		for (int i = 0; i<n-1;i++)
		{
			for (int j=i+1;j<n;j++)
			{
				if (arrivalTime[i]>arrivalTime[j]);
				{
				//swap bt
					
					int temp = arrivalTime[j];
					arrivalTime[i]=arrivalTime[j];
					arrivalTime[j]=temp;
					
				//swap Bt
					temp = burstTime[i];
					burstTime[i] = burstTime[j];
					burstTime[j] = temp;
					
				// swap ids
					temp = processID[i];
					processID[i] =processID[j];
					processID[j] = temp;
					
				}
			}
		}
		// calculate ct ,tat, wt
		int currentTime = 0;
		double totalTAT = 0, totalWT=0;
		for (int i=0;i<n; i++)
		{
			if(currentTime<arrivalTime[i])
			{
				currentTime=arrivalTime[i];//cpu waits
				
			}
		completionTime[i]=currentTime+ burstTime[i];
		currentTime= completionTime[i];
		
		turnaroundTime[i]=completionTime[i]-arrivalTime[i];
		waitingTime[i]= turnaroundTime[i] - burstTime[i];
		
		totalTAT+=turnaroundTime[i];
		totalWT+= waitingTime[i];
		}
		
		//output table
		System.out.println("\n process");
		System.out.println("\n tAT");
		System.out.println("\n tBT");
		System.out.println("\n tCT");
		System.out.println("\n tTAT");
		System.out.println("\n tWT");
		
		for (int i=0;i<n;i++)
		{
			System.out.println("p"+processID[i] + "\t"+arrivalTime[i]+ "\t"+ burstTime[i]+ "\t"+ 
								completionTime[i]+ "\t"+ turnaroundTime[i]+"\t"+waitingTime[i]);
		}
		//averages
		System.out.printf("\n average turnaround Time:%.2f", totalTAT /n);
		System.out.printf("\n average waiting Time:%.2f", totalWT /n);
		
		sc.close();
		}
		
	}





















