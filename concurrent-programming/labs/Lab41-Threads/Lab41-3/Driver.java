public class Driver {
    public static void main(String[] args) throws InterruptedException {
        SharedVariable mySv = new SharedVariable();
        mySv.setVal(0);

        Writer w = new Writer(mySv);
        Reader r = new Reader(mySv);
        w.start();
        r.start();
        w.join();
        r.join();
    }
}
