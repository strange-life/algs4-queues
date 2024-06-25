import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        if (k == 0) return;

        int count = 0;
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            count += 1;

            if (count > k) {
                if (StdRandom.uniformInt(count) >= k) continue;
                queue.dequeue();
            }

            queue.enqueue(s);
        }

        for (String s : queue) {
            StdOut.println(s);
        }
    }
}
