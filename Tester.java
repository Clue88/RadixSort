/******************************************************************************
 *  Compilation:  javac Tester.java
 *  Execution:    java Tester [DEBUG]
 *
 *  DEBUG: Whether to add extra outputs (seed, error output). Defaults true.
 ******************************************************************************/

public class Tester {
    public static int ERR = 0;
    public static boolean DEBUG = true;
    public static void main(String[] args) {
        if (args.length > 0 && Boolean.parseBoolean(args[0]) == false) DEBUG = false;
        String test = "";

        test = "Radix.nth(int n, int col)";
        try {
            check(test, Radix.nth(12902, 2), 9);
        } catch(RuntimeException e) {
            except(test, e);
        }

        test = "Radix.length(int n)";
        try {
            check(test, Radix.length(12902), 5);
        } catch(RuntimeException e) {
            except(test, e);
        }

        test = "Radix.merge(SortableLinkedList original, SortableLinkedList[] buckets)";
        try {
            SortableLinkedList a = new SortableLinkedList();
            SortableLinkedList b = new SortableLinkedList();
            SortableLinkedList c = new SortableLinkedList();
            SortableLinkedList d = new SortableLinkedList();
            SortableLinkedList e = new SortableLinkedList();
            SortableLinkedList f = new SortableLinkedList();

            SortableLinkedList[] buckets = {b, c, d, e, f};

            a.add(1);
            a.add(2);
            a.add(3);

            for (int i = 0; i < buckets.length; i++) {
                buckets[i].add(10 + i);
                buckets[i].add(20 + i);
                buckets[i].add(30 + i);
            }

            Radix.merge(a, buckets);
            check(test, a.toString(), "[1, 2, 3, 10, 20, 30, 11, 21, 31, 12, 22, 32, 13, 23, 33, 14, 24, 34]");
            check(test, b.toString(), "[]");
            check(test, e.toString(), "[]");
        } catch(RuntimeException e) {
            except(test, e);
        }

        if (ERR == 0) System.out.println("All good!");
        else if (ERR == 1) System.out.println("Uh oh... 1 error found.");
        else System.out.println("Uh oh... " + ERR + " errors found.");
    }

    public static void check(String test, Object actual, Object expected) {
        if (actual == null || expected == null) {
            if (actual != null) {
                System.out.println("Failure on " + test + ": Expected \"" +
                                expected + "\", got \"" + actual + "\".");
                ERR++;
            }
            return;
        }

        if (!actual.equals(expected)) {
            System.out.println("Failure on " + test + ": Expected \"" +
                                expected + "\", got \"" + actual + "\".");
            ERR++;
        }
    }

    public static void check(String test, Object actual, Object expected, int seed) {
        if (actual == null || expected == null) {
            if (actual != null) {
                System.out.print("Failure on " + test + ": Expected \"" +
                                expected + "\", got \"" + actual + "\".");
                if (DEBUG) System.out.println(" Seed: " + seed);
                else System.out.println();
                ERR++;
            }
            return;
        }
        
        if (!actual.equals(expected)) {
            System.out.print("Failure on " + test + ": Expected \"" +
                                expected + "\", got \"" + actual + "\".");
            if (DEBUG) System.out.println(" Seed: " + seed);
            else System.out.println();
            ERR++;
        }
    }

    public static void except(String test, RuntimeException e) {
        System.out.println("Failure on " + test + ": RuntimeException");
        if (DEBUG) System.out.println(e.toString());
        ERR++;
    }

    public static void noException(String test, String expected) {
        System.out.println("Failure on " + test + ": Expected " + expected);
        ERR++;
    }

    public static void nothing(Object... nothings) {
        return;
    }
}
