

import java.util.List;
import java.util.Set;

/**
 * A slightly improved version of the greedy method for DNA sequencing in which the
 * initial fragment is picked at random, and in each iteration the sequence is extended from one of its
 * two ends.
 *
 * @author ccottap
 * @version 1.0
 */
public class NaiveBidirectionalGreedyPathSequencing extends NaiveGreedyPathSequencing {

    @Override
    protected int chooseNext(List<Integer> path, Set<Integer> reads) {
        int forward = super.chooseNext(path, reads);
        int last = path.get(path.size() - 1);
        int bestForward = overlaps[last][forward];

        // TODO complete this method
        // It relies on the method of the superclass to get the next best fragment
        // in the forward direction (i.e., to be appended after the last one placed).
        // It just needs to get the best fragment in the backwards direction (i.e., to be
        // appended before the first one placed) and return the best of them two. Recall
        // that returning a positive value will denote a forward extension, and a negative
        // value will denote a backward extension. In the latter case, the value returned
        // should be -(id+1), since the id of a fragment can be 0.

        // Obtener el mejor fragmento en la dirección inversa (backwards).
        int backward = getBestBackwardFragment(path, reads);
        int bestBackward = overlaps[backward][path.get(0)];

        // Devolver el mejor de los dos, positivo para extensión hacia adelante y negativo para extensión hacia atrás.
        if (bestForward >= bestBackward) {
            return forward;
        } else {
            // Devolver -(id+1) para indicar extensión hacia atrás.
            return -(backward + 1);
        }
    }

    private int getBestBackwardFragment(List<Integer> path, Set<Integer> reads) {
        // Implementar la lógica para obtener el mejor fragmento en la dirección inversa.
        // Puedes seguir una lógica similar a chooseNext en la clase NaiveGreedyPathSequencing.
        int last = path.get(0); // Primer fragmento en el camino original.
        int bestBackward = Integer.MIN_VALUE;
        int backward = -1;

        for (int fragment : reads) {
            if (!path.contains(fragment)) {
                int overlap = overlaps[fragment][last];
                if (overlap > bestBackward) {
                    bestBackward = overlap;
                    backward = fragment;
                }
            }
        }

        return backward;
    }

    @Override
    public String getName() {
        return "NaiveBidirectionalGreedyPathSequencing";
    }

}
