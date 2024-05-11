import java.util.Random;

public class Consumer extends Thread {

    private int nIterations;
    private Buffer buffer;
    private Random rnd = new Random();

    public Consumer(Buffer b, int i) {
        this.buffer = b;
        this.nIterations = i;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < nIterations; i++) {
                int data = buffer.takes();
                sleep(rnd.nextInt(500));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
