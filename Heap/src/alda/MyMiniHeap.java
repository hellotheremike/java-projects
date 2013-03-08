// BinaryHeap class
//
// CONSTRUCTION: with optional capacity (that defaults to 100)
//               or an array containing initial items
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// Comparable deleteMin( )--> Return and remove smallest item
// Comparable findMin( )  --> Return smallest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements a binary heap.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
package alda;

public class MyMiniHeap<AnyType extends Comparable<? super AnyType>> implements MiniHeap<AnyType>
{
	
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;      // Number of elements in heap
    private AnyType [ ] array; // The heap array
    private int d;
    
    /**
     * Construct the binary heap.
     */
    //Changed
    public MyMiniHeap( )
    {
        this(2, DEFAULT_CAPACITY );
    }
    
    //Changed
    public MyMiniHeap(int children)
    {
        this(children, DEFAULT_CAPACITY );
    }

    /**
     * Construct the binary heap.
     * @param capacity the capacity of the binary heap.
     */
    //Changed
    @SuppressWarnings("unchecked")
    public MyMiniHeap(int children,  int capacity )
    {
        if (children < 2)
            throw new IllegalArgumentException();
        
    	d = children;
        currentSize = 0;
        array = (AnyType[]) new Comparable[ capacity + 1 ];
    }
    
    /**
     * Construct the binary heap given an array of items.
     */ 
    //Changed
    public MyMiniHeap( AnyType [ ] items )
    {
    	this(2, items);
    }
    //Changed
    @SuppressWarnings("unchecked")
    public MyMiniHeap(int children, AnyType [ ] items )
    {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[ ( currentSize + 2 ) * 11 / 10 ];
        
        int i = 1;
        for( AnyType item : items )
            array[ i++ ] = item;
        buildHeap( );
    }

    /**
     * Insert into the priority queue, maintaining heap order.
     * Duplicates are allowed.
     * @param x the item to insert.
     */
    //Changed
    public void insert( AnyType x )
    {
        if (x == null)
            throw new IllegalArgumentException();
        
        if( currentSize == array.length - 1 )
            enlargeArray( array.length * 2 + 1 );

            // Percolate up
        int hole = ++currentSize;
        for( ; hole > 1 && x.compareTo( array[getParent(hole)] ) < 0; hole = getParent(hole) )
            array[ hole ] = array[getParent(hole)];
        array[ hole ] = x;
    }

    @SuppressWarnings("unchecked")
    private void enlargeArray( int newSize )
    {
        AnyType [] old = array;
        array = (AnyType []) new Comparable[ newSize ];
        for( int i = 0; i < old.length; i++ )
            array[ i ] = old[ i ];        
    }
    
    /**
     * Find the smallest item in the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new IllegalStateException( );
        return array[ 1 ];
    }

    /**
     * Remove the smallest item from the priority queue.
     * @return the smallest item, or throw an UnderflowException if empty.
     */
    //Changed
    public AnyType deleteMin( )
    {
        if( isEmpty( ) )
            throw new IllegalStateException( );

        AnyType minItem = findMin( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }
    //Changed
    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    private void buildHeap( )
    {
        for( int i = currentSize / d; i > 0; i-- )
            percolateDown( i );
    }

    /**
     * Test if the priority queue is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return currentSize == 0;
    }

    /**
     * Make the priority queue logically empty.
     */
    public void makeEmpty( )
    {
        currentSize = 0;
    }

    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    
    //Changed
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; getChild(hole) <= currentSize; hole = child )
        {
        	//The element to check
            child = getChild(hole);
            //A Int that holds the starting position of the children
            int children = getChild(hole); 
            
            //Iterates over all the children(d), and a second 
            //condition that stops the iteration if a subtree isn't complete
            for(int i = 0; i < d && children != currentSize; i++, children++){
                
            	//Checks if the element we want to check is larger than the next element
            	//if so, increase the child Int, witch will result jump to the next branch
            	if(array[child].compareTo(array[children + 1]) > 0){
                    child = children + 1;
                }
            	
            }
            if( array[ child ].compareTo( tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }

    public static void main( String [ ] args )
    {
        int numItems = 10000;
        MyMiniHeap<Integer> h = new MyMiniHeap<Integer>( );
        int i = 37;

        for( i = 37; i != 0; i = ( i + 37 ) % numItems )
            h.insert( i );
        for( i = 1; i < numItems; i++ )
            if( h.deleteMin( ) != i )
                System.out.println( "Oops! " + i );
    }

    //Changed
    @Override
	public int size() {
		return currentSize;
	}
    //Changed
	@Override
	public int getChild(int parent) {
		return parent * d + (2 - d);
	}
	//Changed
	@Override
	public int getParent(int child) {
		return (child - 2 + d)/d;
	}
}
