package delegation;
public class main {
	public static void main(String[] args)
	{
		// Initiate new Prototype instance
		Prototype p1 = new Prototype();
		
		// Set prototype to contain following function, with 0 arguments
		p1.set("func",  new Function(){
			public  void run(Prototype self, Prototype...args){
				System.out.println("Delegated from prototype");
			}
		}, 0);
		
		// Initiate a second Prototype instance
		Prototype p2 = new Prototype();
		
		// Set its parent to be p1, this will allow the program ,
		// to search the p1 for methods it the doesn't exist in p2
		p2.m_prototype = p1;
		p2.set("func_2",  new Function(){
			public  void run(Prototype self, Prototype...args){
				System.out.println("No parameters");
			}
		}, 0);
		
		// Declares the same function, in the same prototype instance, 
		// with another number of arguments, this will result in an overload when called upon
		p2.set("func_2",  new Function(){
			public  void run(Prototype self, Prototype...args){
				
				// Print the content of the first parameter
				System.out.println(args[0].m_data.toString());
			}
		}, 1);
		
		
		invoke(p2, "func");							// Will search p1 for the function 'func' 
		invoke(p2, "func_2");						// Will run the first declared func_2, cause it doesen't have any in-arguments
		invoke(p2, "func_2", new Prototype("One parameter"));	// Will run the second decoration of func_2, cause we provide one in-parameter
		
	}
	
	// Invoke function that executes method based on in-parameter 'method'
	public static void invoke(Prototype obj, String method, Prototype...args)
	{
		// Save reference to prototype to be executed
		Prototype tmp_prototype = obj;
		
		/* 
		 * As long as as the current prototypes HashMap doesn't contain methodname as key,
		 * Check the prototypes prototypes HashMap contains methodname as key
		 */
		while(!tmp_prototype.m_dict.containsKey(method))
		{
			// Move reference from tmp_prototype to its prototype
			tmp_prototype = tmp_prototype.m_prototype;
			
			// If tmp_prototype is null, the requested methodname does not exist
			if(tmp_prototype == null){
				System.out.println("Error");
				return;
			}
		}
		
		// On keymatch in HashMap, run get-method, that retrieves the stored function,
		// based on methodname as key in HashMap and number of arguments to stored function
		tmp_prototype.get(method, args.length).run(tmp_prototype, args);
	}
}