public class SharedVariable {
    public int turn = 0;
    private int val;

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void inc() {
        val++;
    }
}
