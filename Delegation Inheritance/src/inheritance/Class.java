package inheritance;
// Import the library hashmap and arraylist datastructures
import java.util.HashMap;
import java.util.ArrayList;

/* Represents a class
 * Has a set of functions defined by name and parameter count that can be run
 */
public class Class {
	
	// Reference to this class superclass
	// If null, then it has no superclass
	public Class m_superclass;
	
	// Stores a number of arraylists which in turns contains all available functions
	// The functions are grouped by there name so each arraylist contains 
	// the different signatures for functions with the same name
	public HashMap<String, ArrayList<Function>> m_dict;
	
	// Creates a new instance of a class
	public Class(){
		m_dict = new HashMap<String, ArrayList<Function>>();
	}
	
	// Tries to get a function from the class, based on its name and amount of parameters
	public Function get(String method, int pos){
		
		// First make sure that the class contains an entry for the specified name
		if(m_dict.containsKey(method)){
			// Iterate over each function 
			for(Function f:  m_dict.get(method))
				// If the parameters specified and the functions expected parameters are the same its a match
				if(f.params == pos)
					return f;
		}
		
		// No function found, returning null
		return null;
	}

	// Stores a new function in this class with a set of parameters. 
	public void set(String method, Function fn, int paramCount){
		
		// Set the instance of the functions params-field to the paramCount
		// (( As the function instance is send as a labda, it could now be done when is was instanciated))
		fn.params = paramCount;
		
		// Check if the hashmap already contains a record for this method name
		// If now, create the entry
		if(!m_dict.containsKey(method)){
			m_dict.put(method, new ArrayList<Function>());
		}
		
		// Add the function the list of functions
		m_dict.get(method).add(fn);
	}
}
