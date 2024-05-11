public class Driver {

    private static final int N = 5;

    public static void main(String[] args) throws InterruptedException {
        Buffer b = new Buffer(N);
        Consumer c = new Consumer(b, 25);
        Producer p = new Producer(b, 25);
        p.start();
        c.start();
        p.join();
        c.join();

        System.out.println("Finished.");
    }
}