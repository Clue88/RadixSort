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
        int maxLength = 0;
        SortableLinkedList[] buckets = new SortableLinkedList[10];
        for (int i = 0; i < buckets.length; i++) buckets[i] = new SortableLinkedList();

        while (data.size() > 0) {
            int digit = nth(data.get(0), 0);
            buckets[digit].add(data.get(0));
            if (data.get(0) > maxLength) maxLength = data.get(0);
            data.remove(0);
        }
        Radix.merge(data, buckets);

        maxLength = length(maxLength);

        for (int i = 1; i < maxLength; i++) {
            while (data.size() > 0) {
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
        SortableLinkedList zeroes = new SortableLinkedList();

        for (int i = 0; i < data.size(); i++) {
            int num = data.get(i);
            if (num > 0) positives.add(num);
            else if (num < 0) negatives.add(num * -1);
            else zeroes.add(num);
        }

        radixSortSimple(positives);
        radixSortSimple(negatives);

        for (int i = data.size() - 1; i >= 0; i--) {
            data.remove(i);
        }

        for (int i = negatives.size() - 1; i >= 0; i--) {
            data.add(negatives.get(i) * -1);
        }

        data.extend(zeroes);
        data.extend(positives);
    }
}
