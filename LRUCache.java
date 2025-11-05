package spos;

import java.util.*;

class LRUPageReplacement {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUPageReplacement(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public boolean refer(int pageNumber) {
        boolean pageFault = false;

        if (!cache.containsKey(pageNumber)) {
            pageFault = true;
            if (cache.size() == capacity) {
                int oldestPage = cache.entrySet().iterator().next().getKey();
                cache.remove(oldestPage);
            }
        }

        cache.put(pageNumber, pageNumber);
        return pageFault;
    }

    public void displayCache() {
        System.out.print("Frames: ");
        for (int p : cache.keySet())
            System.out.print(p + " ");
        System.out.println();
    }
}

public class LRUCache {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter number of pages: ");
        int n = sc.nextInt();

        int[] pages = new int[n];
        System.out.println("Enter the page reference string:");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();

        LRUPageReplacement lru = new LRUPageReplacement(frames);
        int pageFaults = 0;

        for (int p : pages) {
            System.out.print("Refer page " + p + " -> ");
            if (lru.refer(p)) {
                pageFaults++;
                System.out.print("Page Fault! ");
            }
            lru.displayCache();
        }

        System.out.println("\nTotal Page Faults: " + pageFaults);
        sc.close();
    }
}
