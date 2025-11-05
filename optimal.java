package spos;

import java.util.*;

class OptimalPageReplacement {
    private final int capacity;
    private final List<Integer> frames;

    public OptimalPageReplacement(int capacity) {
        this.capacity = capacity;
        this.frames = new ArrayList<>(capacity);
    }

    public int simulate(int[] pages) {
        int pageFaults = 0;

        System.out.println("\n--- OPTIMAL PAGE REPLACEMENT SIMULATION ---\n");

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];
            System.out.print("Refer page " + page + " -> ");

            // If page already in frames — no fault
            if (frames.contains(page)) {
                System.out.print("No Fault. ");
            } else {
                pageFaults++;

                // If space available — add directly
                if (frames.size() < capacity) {
                    frames.add(page);
                } else {
                    // Replace using optimal prediction
                    int pageToReplace = predict(pages, i + 1);
                    frames.remove(Integer.valueOf(pageToReplace));
                    frames.add(page);
                }
                System.out.print("\u001B[31mPage Fault!\u001B[0m "); // red text
            }

            displayFrames();
        }

        // Return total faults
        return pageFaults;
    }

    // Predict which page to replace
    private int predict(int[] pages, int index) {
        int farthest = -1;
        int replacePage = -1;

        for (int page : frames) {
            int j;
            for (j = index; j < pages.length; j++) {
                if (page == pages[j]) {
                    if (j > farthest) {
                        farthest = j;
                        replacePage = page;
                    }
                    break;
                }
            }
            if (j == pages.length) {
                // Page not used again — best to replace
                return page;
            }
        }

        return (replacePage == -1) ? frames.get(0) : replacePage;
    }

    private void displayFrames() {
        System.out.print("Frames: ");
        for (int p : frames) System.out.print(p + " ");
        System.out.println();
    }
}

public class OPTIMAL {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference numbers:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        OptimalPageReplacement opt = new OptimalPageReplacement(frames);
        int pageFaults = opt.simulate(pages);

        // Add space and highlight the total result
        System.out.println("\n===================================");
        System.out.println("\u001B[32m✅ Total Page Faults: " + pageFaults + "\u001B[0m");
        System.out.println("===================================\n");

        sc.close();
    }
}
