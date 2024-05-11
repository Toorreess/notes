public class Fibonacci extends Thread {
    int id;
    Fibonacci[] arrayF;
    int[] results;

    public Fibonacci(int i, Fibonacci[] arrayF, int[] results) {
        id = i;
        this.arrayF = arrayF;
        this.results = results;
    }

    @Override
    public void run() {
        if (id < 2) {
            results[id] = 1;
        } else {
            while (arrayF[id - 2].isAlive())
                Thread.yield();
            while (arrayF[id - 1].isAlive())
                Thread.yield();
            results[id] = results[id - 1] + results[id - 2];
        }

    }
}
