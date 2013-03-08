package alda;

import java.util.Random;

public class MySkiplist<T extends Comparable<? super T>> {
	private int size; //Number of elements variable
	private Node<T> start; //The start node with the maxLevel
	
	public MySkiplist(){
		this(3);
	}	
    public MySkiplist(int level) {
        start = new Node<T>(null, level);
        this.size = 0;
    }
	
    /*
     * The generic NodeClass
     */
	private class Node<T> {
		private T data;
		private int level;
		// The list that holds the elemnts next elements.
		private Node<T>[] next;
		
		@SuppressWarnings("unchecked")
		public Node(T data, int lvl) {
			this.level = lvl;
			this.data = data;
			//Initiates the Array to a given size
			this.next = (Node<T>[]) new Node[level];
		}
		
		public void set(int level, Node<T> node){
			//Initiate a pointer to an element at a given level.
			this.next[level] = node;
		}
	} 
	
	/*
	 * Return the amouth of elements in the SkipList
	 */
	
    public int size(){
    	return this.size;
    }
    
    /*
     * Adds a new element to the list
     */
    public boolean add(T data){
    	//If element allready exist, do not add
    	if(contains(data))
    		return false;
    	//Current that is the first element in our list (contains nulll)
    	//Is the node that we will start from
    	Node<T> current = start;
    	Node<T> newNode = new Node<T>(data, coninLevelToss());
    	int level = current.level-1;
    	
    	//Lopps untill the level reaches 0, by then the node is added.
    	while(level >= 0){
    		//Grabs the next element at the current level
    		Node<T> next = current.next[level];
    		/*
    		* If the next element is null then it doesent exist.
    		* If our new element has the same level-height as our loop 
    		* level, that will result in that currents next-elemnt, at that level,
    		* will be our new element.
    		*/
    		if(next == null){
    			if(newNode.level > level){
    				newNode.set(level, next);
    				current.set(level, newNode);
    			}
    			level--;
    		}
    		/*
    		 * If the input data is bigger than the currents next nodes data
    		 * we will set the next node to the current node. In this case we are 
    		 * standing behind our newNodes position
    		 */
    		else if(newNode.data.compareTo(next.data) > 0){
    			current = next;
    		}
    		/*
    		 * In this case we are looking at a node that is infornt of out newNodes position
    		 * If our newNode's level is tha same as the loop-level we want to change the pointers
    		 * so that the currents next-element is our newNode, and newNode's next element is the 
    		 * current's privious next node. After that step down a level.
    		 */
    		else{
    			if(newNode.level > level){
    				current.set(level, newNode);
    				newNode.set(level, next);
    			}
    			level--;
    		}
    	}
    	this.size++;
    	return true;
    }
    
    /*
     * Removes a given object. Works the same way as add. The difference is that
     * when we find our element as current's next-element. We remove the pointers to 
     * our requested element and then go down a level.
     */
    public boolean remove(T data){
    	Node<T> current = start;
    	int level = current.level-1;
    	boolean removed = false;
    	
    	while(level >= 0){
    		Node<T> next = current.next[level];
    		if(next == null){
    			level--;
    		}
    		else if(data.compareTo(next.data) == 0){
    			/* Found the element. Change the pointers for the current element 
    			 * to our requested element's next element at the current level 
    			 */
    			current.set(level, next.next[level]);
    			level--;
    			removed = true;
    		}
    		else if(data.compareTo(next.data) > -1){
	    		current = next;
	    	}
	    	else{
	    		level--;
	    	}
    	}	
    	
    	if(removed)
    		this.size--;
    	return removed;
    }
    
    /*
     * Searches if the element exists in the current list.Works the same way as add, 
     * except when we find the element, we will break the loop.
     */
    public boolean contains(T data){	
    	Node<T> current = start;
    	Node<T> next = null;
    	int level = current.level-1;
    	
    	while(level >= 0){
    		next = current.next[level];
    		if(next == null){
    			level--;
    			continue;
    		}
    		else if(data.compareTo(next.data) == 0){
    			//Found the element.
    			break;
    		}
    		else if(data.compareTo(next.data) > 0){
	    		current = next;
	    	}
	    	else{
	    		level--;
	    	}
    	}
    	if(next != null)
    		return true;
    	else
    		//If the element does not exist.
    		return false;
    }
    
    /*
     * Returns the level height of the new element
     */
    private int coninLevelToss(){
    	Random random = new Random();
    	int level = 1;
    	while(random.nextBoolean() && level < start.level){
    		level++;
    	}
    	return level;
    }
    
}
