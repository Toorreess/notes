import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Driver {
    public static final int SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        List<Integer> toSort = new ArrayList<>();
        Random rnd = new Random();

        for (int i = 0; i < SIZE; i++) {
            int data = rnd.nextInt(1000);
            System.out.print(data + " ");
            toSort.add(data);
        }
        System.out.println();

        Node n = new Node(toSort);
        n.start();
        n.join();

        for (int i = 0; i < SIZE; i++) {
            System.out.print(toSort.get(i) + " ");
        }
        System.out.println();
    }
}
