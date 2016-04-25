package sorts;

import java.util.LinkedList;
import java.util.Queue;

public class RadixSort implements GenericSort {
    @Override
    public int[] sort(int[] elements) {
        Queue<Integer> main_queue = new LinkedList<>();
        if (elements.length > 0) {
            int largest = elements[0];
            for (int element : elements) {
                main_queue.add(element);
                largest = largest < element ? element : largest;
            }
            int digits = 0;
            do {
                largest /= 10;
                ++digits;
            } while (largest != 0);
            @SuppressWarnings("unchecked")
            Queue<Integer>[] buckets = new Queue[10];
            for (int i = 0; i < 10; ++i) {
                buckets[i] = new LinkedList<>();
            }
            int den = 1;
            for (int i = 0; i < digits; ++i) {
                while (main_queue.size() > 0) {
                    int temp = main_queue.remove();
                    buckets[(temp / den) % 10].add(temp);
                }
                for (int j = 0; j < 10; ++j) {
                    while (buckets[j].size() > 0) {
                        main_queue.add(buckets[j].remove());
                    }
                }
                den *= 10;
            }
        }
        int i = 0;
        while (main_queue.size() > 0) {
            elements[i] = main_queue.remove();
            i++;
        }
        return elements;
    }
}
