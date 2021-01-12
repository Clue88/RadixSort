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
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i) > maxLength) maxLength = data.get(i);
        }
        maxLength = length(maxLength);

        for (int i = 0; i < maxLength; i++) {
            SortableLinkedList b0 = new SortableLinkedList();
            SortableLinkedList b1 = new SortableLinkedList();
            SortableLinkedList b2 = new SortableLinkedList();
            SortableLinkedList b3 = new SortableLinkedList();
            SortableLinkedList b4 = new SortableLinkedList();
            SortableLinkedList b5 = new SortableLinkedList();
            SortableLinkedList b6 = new SortableLinkedList();
            SortableLinkedList b7 = new SortableLinkedList();
            SortableLinkedList b8 = new SortableLinkedList();
            SortableLinkedList b9 = new SortableLinkedList();
            
            for (int j = 0; j < data.size(); j++) {
                int digit = nth(data.get(j), i);
                if (digit == 0) b0.add(data.get(j));
                else if (digit == 1) b1.add(data.get(j));
                else if (digit == 2) b2.add(data.get(j));
                else if (digit == 3) b3.add(data.get(j));
                else if (digit == 4) b4.add(data.get(j));
                else if (digit == 5) b5.add(data.get(j));
                else if (digit == 6) b6.add(data.get(j));
                else if (digit == 7) b7.add(data.get(j));
                else if (digit == 8) b8.add(data.get(j));
                else b9.add(data.get(j));
            }

            SortableLinkedList[] buckets = {b1, b2, b3, b4, b5, b6, b7, b8, b9};
            Radix.merge(b0, buckets);

            for (int j = data.size() - 1; j >= 0; j--) {
                data.remove(j);
            }
            data.extend(b0);
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
