	
	
	
	import java.util.LinkedList;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Locale;
	import java.util.Set;
	
	import es.uma.ada.problem.statistical.MarkovModel;
	import es.uma.ada.problem.statistical.MaximumLikelihoodEstimator;
	
	/**
	 * Computes a maximum likelihood sequence of states for a hidden Markov model using
	 * Viterbi algorithm
	 * @author ccottap
	 * @version 1.0
	 */
	public class ViterbiMaxLikelihood extends MaximumLikelihoodEstimator {
	
		/**
		 * basic constructor
		 */
		public ViterbiMaxLikelihood() {
			super();
		}
	
		@Override
		protected List<String> _run(MarkovModel mm, List<String> observed) {
			Set<String> states = mm.getStates();
			int n = states.size();
			int T = observed.size();
			ArrayList<String> map = new ArrayList<String>(); // we use this map to know the name of a state given its numerical ID
	
			for (String s: states) {
				map.add(s);				// map.get(i) returns the name of state s_i
			}
			
			double[][] L = new double[n][T];	// optimal values
			int[][] D = new int[n][T];			// optimal decisions
			
			double[][] logtransition = new double[n][n]; // cache of log(transition probabilities)
			for (int i=0; i<n; i++) 
				for (int r=0; r<n; r++) {
					double tp = mm.getTransitionProbability(map.get(i), map.get(r));
					if (tp>0)
						logtransition[i][r] = Math.log(tp);
					else
						logtransition[i][r] = Double.NEGATIVE_INFINITY;
				}
	
			LinkedList<String> seq = new LinkedList<String>();	// At the end, this list will be returned by the method
																// It will contain the maximum-likelihood list of hidden states
	
			// TODO Complete this method
			// 
			// This method has to fill matrices L and D, which respectively contain the optimal 
			// log-likelihood values and the optimal decisions associated to them. To be precise,
			// L(i,j) = max log-likelihood of observations(0..j-1) ending with state i
			// D(i,j) = optimal decision (state from which state i is reached).
			//
			// The Bellman equation for the log-likelihood is:
			// L(i,0) = log (marginal probability of s_i) + log(output probability of observation(0) in state s_i)
			// L(i,j) = max (L(k,j-1)+ log(transition from s_k to s_i)) + log(output probability of observation(j) in state s_i)
			//
			// Note that attempting to compute log(0) will result in NaN, which would cause numerical problems. 
			// To avoid this, check values before computing the log, and use Double.NEGATIVE_INFINITY to
			// represent log(0) = -infinity. Note also that whenever -infinity appears as a term in a sum,
			// the result will be also -infinity, so there is no point in computing other terms of the sum.
			//
			// Once the matrices are filled, the last column of L needs being inspected to identify
			// the optimal final state. Knowing this, the information in D can be used to trace back
			// previous states up to the start of the sequence. This will be stored in seq, and returned.
			
			
			// base case
			for(int i = 0; i < n; i++) {
				L[i][0] = Math.log(mm.getMarginalProbability(map.get(i))) + Math.log(mm.getOutputProbability(map.get(i), observed.get(0)));
				D[i][0] = -1; 
			}
			// general case
			for(int j = 1; j < T; j++) {
				for(int i = 0; i < n; i++) {
					double maxLogLikelihood = Double.NEGATIVE_INFINITY;
					int bestPreviousState = -1;
					
					for(int k = 0; k < n; k++) {
						double transitionLogProb = logtransition[k][i];
						double previousLogLikelihood = L[k][j-1];
						
						if(transitionLogProb != Double.NEGATIVE_INFINITY && previousLogLikelihood != Double.NEGATIVE_INFINITY) {
							double candidateLogLikelihood = previousLogLikelihood + transitionLogProb;
							if(candidateLogLikelihood > maxLogLikelihood) {
								maxLogLikelihood = candidateLogLikelihood;
								bestPreviousState = k;
							}
						}
					}
					
					double outputLogProb = Math.log(mm.getOutputProbability(map.get(i), observed.get(j)));
					if(outputLogProb == Double.NEGATIVE_INFINITY) {
						L[i][j] = Double.NEGATIVE_INFINITY;
					} else {
						L[i][j] = maxLogLikelihood + outputLogProb;
					}
					D[i][j] = bestPreviousState;
				}
			}
			// find the best final state
			int bestFinalStage = -1;
			double maxFinalLogLikelihood = Double.NEGATIVE_INFINITY;
			
			for(int i=0; i < n; i++) {
				if(L[i][T-1] > maxFinalLogLikelihood) {
					maxFinalLogLikelihood = L[i][T-1];
					bestFinalStage = i;
				}
			}
			
			// reconstruct the sequences of states
			int currentState = bestFinalStage;
			for(int j = T-1; j>=0; j--) {
				seq.addFirst(map.get(currentState));
				currentState = D[currentState][j];
			}
			
			if (verbosity) {
				System.out.print("\nL\t\t|");
				for (int i=0; i<T; i++)
					System.out.print("\t" + i);
				System.out.print("\n\t\t|");
				for (int i=0; i<T; i++)
					System.out.print("\t" + observed.get(i));
				System.out.print("\n---\t---\t+");
				for (int i=0; i<T; i++)
					System.out.print("\t---");
				for(int j=0; j<n; j++) {
					System.out.print("\n" + j + "\t" + map.get(j) + "\t|");
					for (int i=0; i<T; i++) {
						System.out.print("\t" + ((L[j][i]>Double.NEGATIVE_INFINITY)?String.format(Locale.US, "%.2f", L[j][i]):"-Inf"));
					}
				}
				System.out.println("\n");
				System.out.print("\nD\t\t|");
				for (int i=0; i<T; i++)
					System.out.print("\t" + i);
				System.out.print("\n\t\t|");
				for (int i=0; i<T; i++)
					System.out.print("\t" + observed.get(i));
				System.out.print("\n---\t---\t+");
				for (int i=0; i<T; i++)
					System.out.print("\t---");
				for(int j=0; j<n; j++) {
					System.out.print("\n" + j + "\t" + map.get(j) + "\t|");
					for (int i=0; i<T; i++) {
						System.out.print("\t" + ((D[j][i]<0)?"--":map.get(D[j][i])));
					}
				}
				System.out.println("\n");
			}
			
			
			return seq;
		}
	
		@Override
		public String getName() {
			return "Viterbi";
		}
	
	}
