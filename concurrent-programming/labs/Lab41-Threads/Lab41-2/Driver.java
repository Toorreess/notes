public class Driver {
    public static void main(String[] args) throws InterruptedException {
        SharedVariable mySv = new SharedVariable();
        MyThread a = new MyThread(mySv);
        MyThread b = new MyThread(mySv);
        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println(mySv.getVal());
    }

}
