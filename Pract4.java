package spos; 
import java.util.*;
public class PageReplacement 
{
static void lru(int[] pages, int capacity) 
 { 
  ArrayList<Integer> frames = new ArrayList<>(capacity); 
  int pageFaults = 0;

System.out.println("\n--- LRU Page Replacement ---"); 
  for (int i = 0; i < pages.length; i++) 
{
int page = pages[i];

if (!frames.contains(page)) 
{
if (frames.size() == capacity) 
{ 
frames.remove(0);
}
frames.add(page); 
pageFaults++;
} 
else 
{
frames.remove((Integer) page); 
frames.add(page);
}
System.out.println("Step " + (i + 1) + ": " + frames);
}
System.out.println("Total Page Faults (LRU): " + pageFaults);
}
static void optimal(int[] pages, int capacity)
{ 
ArrayList<Integer> frames = new ArrayList<>(capacity); 
int pageFaults = 0;
 
System.out.println("\n--- Optimal Page Replacement ---"); 
  for (int i = 0; i < pages.length; i++) 
  {
int page = pages[i];

if (!frames.contains(page)) 
{
if (frames.size() < capacity) 
{ frames.add(page);
}
else 
{
int farthest = i + 1, indexToReplace = -1; 
 for (int j = 0; j < frames.size(); j++) {
int nextUse = Integer.MAX_VALUE;
for (int k = i + 1; k < pages.length; k++) 
{ 
 if (pages[k] == frames.get(j)) 
{
nextUse = k; break;
}
}
if (nextUse > farthest) 
{ farthest = nextUse; 
 indexToReplace = j;
}
}
if (indexToReplace == -1) indexToReplace = 0;
frames.set(indexToReplace, page);
}
pageFaults++;
}
System.out.println("Step " + (i + 1) + ": " + frames);
}
System.out.println("Total Page Faults (Optimal): " + pageFaults);
}
public static void main(String[] args) 
{ 
Scanner sc = new Scanner(System.in);
System.out.print("Enter number of pages: "); 
int n = sc.nextInt();
int[] pages = new int[n];
System.out.print("Enter the page reference string: "); 
for (int i = 0; i < n; i++) 
{
pages[i] = sc.nextInt();
}
System.out.print("Enter frame capacity: "); 
int capacity = sc.nextInt();
lru(pages, capacity);
optimal(pages, capacity);
sc.close();
}
}
