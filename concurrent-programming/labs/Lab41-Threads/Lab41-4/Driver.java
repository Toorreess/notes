public class Driver {
    private static final int N = 10;

    public static void main(String[] args) {
        Fibonacci[] arrayF = new Fibonacci[N];
        int[] results = new int[N];
        for (int i = 0; i < N; i++) {
            arrayF[i] = new Fibonacci(i, arrayF, results);
            arrayF[i].start();
        }
        while (arrayF[N - 1].isAlive())
            Thread.yield();

        System.out.println(results[N - 1]);
    }
}
