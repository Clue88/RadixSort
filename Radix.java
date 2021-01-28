public class Radix {
    public static int nth(int n, int col) {
        return (int) (n / Math.pow(10, col)) % 10;
    }

    public static int length(int n) {
        return (int) Math.log10(n) + 1;
    }

    public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
        for (int i = 0; i < buckets.length; i++) {
            original.extend(buckets[i]);
        }
    }

    public static void radixSortSimple(SortableLinkedList data) {
        int maxLength = 1;
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        for (int i = 0; i < buckets.length; i++) buckets[i] = new SortableLinkedList();

        for (int i = 0; i < maxLength; i++) {
            while (data.size() > 0) {
                if (i == 0 && length(data.get(0)) > maxLength) maxLength = length(data.get(0));
                int digit = nth(data.get(0), i);
                buckets[digit].add(data.get(0));
                data.remove(0);
            }
            Radix.merge(data, buckets);
        }
    }

    public static void radixSort(SortableLinkedList data) {
        SortableLinkedList positives = new SortableLinkedList();
        SortableLinkedList negatives = new SortableLinkedList();

        while (data.size() > 0) {
            int num = data.get(0);
            if (num >= 0) positives.add(num);
            else negatives.add(num * -1);
            data.remove(0);
        }

        radixSortSimple(positives);
        radixSortSimple(negatives);

        while (negatives.size() > 0) {
            data.add(negatives.get(negatives.size() - 1) * - 1);
            negatives.remove(negatives.size() - 1);
        }

        data.extend(positives);
    }
}
