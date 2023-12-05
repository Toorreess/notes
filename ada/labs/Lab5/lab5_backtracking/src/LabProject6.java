import java.io.IOException;


/**
 * Lab project Unit 6: Backtracking
 * @author ccottap
 * @version 1.0
 */
public class LabProject6 {

	/**
	 * Main method to run the lab project.
	 * @param args command-line arguments: (-f|-t) <i>parameters</i> 
	 * The options are:
	 * <ul>
	 * <li> -f <i>filename cover-size</i>: reads a graph from a file and tries to find a vertex cover of the given size. </li>
	 * <li> -t <i>vi vf vinc numtests</i> : generates graphs from size vi to size vf in steps of vinc. For each size, numtests
		graphs are generated (in all cases the graphs are random, where each edge has a probability of 2/N, where N is the 
		corresponding number of vertices), and the algorithm is run for vertex cover sizes ranging from 1 to N vertices. The
		number of nodes explored and the solvability of each instance is measured and dumped to a file for subsequent analysis.</li>
	 * </ul>
	 * @throws IOException if data cannot be read/written from/to disk.
	 */
	public static void main(String[] args) throws IOException {
		TestVertexCoverBacktracking.run(args);
	}

}
