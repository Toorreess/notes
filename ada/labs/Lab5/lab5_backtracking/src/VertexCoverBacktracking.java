

import java.util.HashSet;
//import java.util.List;
import java.util.Set;

import es.uma.ada.backtracking.Backtracking;
import es.uma.ada.datastructures.graph.Graph;
import es.uma.ada.datastructures.tuple.Pair;
import es.uma.ada.problem.combinatorial.set.vertexcover.VertexCover;

/**
 * Uses backtracking to find a vertex cover
 * @author ccottap
 * @version 1.0
 */
public class VertexCoverBacktracking extends Backtracking {
	/**
	 * the vertex cover instance to be solved
	 */
	private Graph<Integer> g;
	/**
	 * maximum number of vertices in the cover
	 */
	private int k;

	/**
	 * the solution found
	 */
	private Set<Integer> solution;

	/**
	 * Sets the vertex cover instance to be solved, and uses the number of vertices
	 * as the maximum size of the vertex cover sought. This can be later changed using the function 
	 * {@link  VertexCoverBacktracking#setMaxSize(int)  setMaxSize}.
	 * @param g an undirected graph
	 */
	public void setInstance(Graph<Integer> g) {
		this.g = g;
	}

	/**
	 * Sets the maximum size of the vertex cover
	 * @param k the maximum size of the vertex cover
	 */
	public void setMaxSize(int k) {
		this.k = k;
	}


	@Override
	protected Object initialState() {
		// Each node in the search tree represents a pair (S, v), where
		// S is the current set of vertices and i is the next vertex to be considered
		Set<Integer> initialSet = new HashSet<>();
		int initialVertex = 0;
		Pair<Set<Integer>, Integer> initialState = new Pair<>(initialSet,initialVertex);

		return initialState;
	}

	@Override
	protected boolean backtracking(Object state) {
		@SuppressWarnings("unchecked")
		Pair<Set<Integer>, Integer> p = (Pair<Set<Integer>, Integer>) state;
		Set<Integer> partialSolution = p.getFirst();
		int next = p.getSecond();
		boolean ok = false;

		nodes ++;
		
		if(partialSolution.size() > k) {
			return false;
		} else if (partialSolution.size() < k && next > g.numVertices()) {
			return false;
		} else {
			if (isVertexCover(partialSolution)) {
	            solution = new HashSet<Integer>(partialSolution);
	            return true;
	        } else {
	        	if(!(partialSolution.size() < k && next <= g.numVertices())) return false;
	        	if(uncG(next, partialSolution).isEmpty()) {
	        		ok = backtracking(new Pair<>(partialSolution, next+1));
	        	} else {
	        		Set<Integer> nextState = new HashSet<>(partialSolution);
	        		nextState.add(next);
	        		ok = ok || backtracking(new Pair<>(nextState, next+1));
	        		
	        		Set<Integer> nextStateUnc = new HashSet<>(partialSolution);
	        		nextStateUnc.addAll(uncG(next, partialSolution));
	        		ok = ok || backtracking(new Pair<>(nextStateUnc, next+1));
	        	}
	        }
		}

        return ok;
    }

    private Set<Integer> uncG(int vertex, Set<Integer> partialSolution) {
        Set<Integer> neighbors = g.getSuccessors(vertex);
        neighbors.removeAll(partialSolution);
        return neighbors;
    }

    private boolean isVertexCover(Set<Integer> partialSolution) {
        // Check if uncG(v, S) = ∅ for all v ∈ V
        for (int vertex : g.getVertices()) {
            if (!partialSolution.contains(vertex)) {
                Set<Integer> uncoveredNeighbors = uncG(vertex, partialSolution);
                if (!uncoveredNeighbors.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
	
	@Override
	public String getName() {
		return "VertexCoverBacktracking";
	}

	/**
	 * Returns the solution found (null, if no solution was found)
 	 * @return the solution found (null, if no solution was found)
	 */
	public Set<Integer> getSolution() {
		return solution;
	}

}
