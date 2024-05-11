import java.util.List;
import java.util.ArrayList;

public class Node extends Thread {
    private List<Integer> list;

    public Node(List<Integer> l) {
        this.list = l;
    }

    protected void add(List<Integer> l, int from, int to) {
        l.addAll(list.subList(from, to));
    }

    protected void merge(List<Integer> la, List<Integer> lb) {
        list.clear();
        int i = 0;
        int j = 0;
        while ((i < la.size()) && (j < lb.size())) {
            if (la.get(i) < lb.get(j))
                list.add(la.get(i++));
            else
                list.add(lb.get(j++));

        }
        while (i < la.size())
            list.add(la.get(i++));
        while (j < lb.size())
            list.add(lb.get(j++));
    }

    @Override
    public void run() {
        if (list.size() <= 1)
            return;
        try {
            List<Integer> la = new ArrayList<>();
            List<Integer> lb = new ArrayList<>();

            add(la, 0, list.size() / 2);
            add(lb, list.size() / 2, list.size());
            Node a = new Node(la);
            Node b = new Node(lb);
            a.start();
            b.start();
            a.join();
            b.join();
            merge(la, lb);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
