package delegation;
import java.util.*;

/*
 * Prototype class that provides the functionality to have a parent object
 */
public class Prototype {
	public Prototype m_prototype;							// Reference to the parent object
	public HashMap<String, ArrayList<Function>> m_dict;		// HashMap that stores an ArrayList of functions and string key
	public Object m_data;									// Provides data storage
	
	/*
	 * Constructor that initiates the HashMap 
	 */
	public Prototype(){
		m_dict = new HashMap<String, ArrayList<Function>>();
	}
	
	/*
	 * Constructor that declares value to object
	 */
	public Prototype(Object data){
		m_data = data;
	}
	
	/*
	 * Get function that contains the logic to fetch the correct object
	 */
	public Function get(String method, int pos){
		
		// If the method name doesn't exist in HashMap, return null
		if(m_dict.containsKey(method))
			
			// Iterate over all objects in the ArrayList, 
			for(Function f:  m_dict.get(method))
				
				 /*  
				  * Check if the member variable 'params' of class 'Function',
				  * that saves the in parameter count,
				  * corresponds with number of in parameters. 
				  */
				if(f.params == pos)
					//Found matching object, return
					return f;
		
		//No match or none existing key, return null
		return null;
	}

	/*
	 * Set function that takes a object and type Function and Stores it in the HashMap
	 */
	public void set(String method, Function fn, int paramCount){
		// Set number of expected parameters for function
		fn.params = paramCount;
		
		//If the key doesen't exist, initiate the ArrayList at the key, that is the function name
		if(!m_dict.containsKey(method)){
			m_dict.put(method, new ArrayList<Function>());
		}
		
		// Fetching the HashValue based on the function name, that retrieves an ArrayList
		// After that add function to ArrayList
		m_dict.get(method).add(fn);
	}
}
