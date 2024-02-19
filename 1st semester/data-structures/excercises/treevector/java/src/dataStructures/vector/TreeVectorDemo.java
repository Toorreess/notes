package dataStructures.vector;

import dataStructures.list.List;

public class TreeVectorDemo {	
	public static void printlnVector(TreeVector<?> v) {
		System.out.print("TreeVector(");
		for(int i=0; i<v.size()-1; i++)
			System.out.print(v.get(i)+",");
		if(v.size()>0)
			System.out.print(v.get(v.size()-1));
		System.out.println(")");
	}
	
    public static void main(String[] args) {
    	// Construct a tree vector
    	int elem = 0;
    	int n = 4;
    	TreeVector<Integer> v = new TreeVector<Integer>(n, elem);
    	printlnVector(v);
    	
    	// check size
    	if(v.size() != (int) Math.pow(2, n))
    		System.out.println("Error in size: "+v.size());
    	
    	// Check initial value for all elements
    	for(int i=0; i<v.size(); i++)
    		if(v.get(i) != elem)
    			System.out.println("Error in get using index "+i);
    	    	
    	// Modify all elements and check   	
    	for(int i=0; i<v.size(); i++) {
    		v.set(i, i*10);
    		printlnVector(v);
    		for(int j=0; j<v.size(); j++)
    			if(j<=i) {
    				if(v.get(j) != j*10)
    					System.out.println("Error in get using index "+j);
    			} else {
    				if(v.get(j) != elem)
    					System.out.println("Error in get using index "+j);
    			}
    	}
    	printlnVector(v);
    	
    	// Construct a list with vector's elements
    	List<Integer> xs = v.toList();
    	System.out.println(xs);
    	
    	// Check values in list
    	for(int i=0; i<v.size(); i++) 
    		if(v.get(i) != xs.get(i))
    			System.out.println("Error in list using index "+i);
    }
}
