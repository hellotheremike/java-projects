package inheritance;
/* Represents a instance of a class
 * contains a reference to what class its of and for testing cases a general data field
 */
public class Instance {
	
	// Determines what class this instance is of
	 public Class m_class;
	 
	 // Stores some simple data
	 public Object m_data;
	 
	 // Empty constructor
	 public Instance()
	 {
		 this.m_data = null;
	 }
	 
	 // Creates an instance with a set data
	 public Instance(Object data)
	 {
		 this.m_data = data;
	 }
	 
	 
}
