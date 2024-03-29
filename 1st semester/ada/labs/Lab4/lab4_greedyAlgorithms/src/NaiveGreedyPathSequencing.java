

import java.util.List;
import java.util.Set;

import es.uma.ada.greedy.permutation.dnasequencing.GreedyPathSequencing;

/**
 * A simple version of the greedy method for DNA sequencing in which the 
 * initial fragment is picked at random, and the next fragment is in each iteration
 * the one with the largest overlap with the last selected one (in case of a tie, 
 * the fragment with the lowest ID is picked). 
 * @author ccottap
 * @version 1.0
 */
public class NaiveGreedyPathSequencing extends GreedyPathSequencing {

	@Override
	protected int chooseInitial(Set<Integer> reads) {
        int randomIndex = rng.nextInt(reads.size());
		return (int) reads.toArray()[randomIndex];
	}

	@Override
	protected int chooseNext(List<Integer> path, Set<Integer> reads) {

        int lastFragment = path.get(path.size() - 1);
        int next = -1;

        int maxOverlap = Integer.MIN_VALUE;

        for (int fragment : reads) {
            if (!path.contains(fragment)) {
                int overlap = overlaps[lastFragment][fragment];
                if (overlap > maxOverlap || (overlap == maxOverlap && fragment < next)) {
                    maxOverlap = overlap;
                    next = fragment;
                }
            }
        }

        return next;
	}

	@Override
	public String getName() {
		return "NaiveGreedyPathSequencing";
	}

}
