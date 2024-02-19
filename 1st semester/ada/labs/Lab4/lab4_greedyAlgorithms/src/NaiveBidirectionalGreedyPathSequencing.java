

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

        int backward = getBestBackwardFragment(path, reads);
        int bestBackward = overlaps[backward][path.get(0)];

        if (bestForward >= bestBackward) {
            return forward;
        } else {
            return -(backward + 1);
        }
    }

    private int getBestBackwardFragment(List<Integer> path, Set<Integer> reads) {
        int last = path.get(0);
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
