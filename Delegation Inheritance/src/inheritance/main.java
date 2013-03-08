package inheritance;
import delegation.Prototype;

public class main {
	public static void main(String[] args)
	{
		// Initiate a new instance of class Instance
		Instance i1 = new Instance();
		
		//Initiate a new class instance
		Class c1 = new Class();
		
		// Set class to contain following function, with 0 arguments
		c1.set("func", new Function(){
			public void run(Instance self, Instance...args){
				System.out.println("Inherited from superclass");
			}
		}, 0);
		// Set instance i1 to contain the class-instance c1
		i1.m_class = c1;
		
		
		
		Instance i2 = new Instance();
		Class c2 = new Class();
		c2.set("func_2", new Function(){
			public void run(Instance self, Instance...args){
				System.out.println("No paramaters");
			}
		}, 0);
		
		// Declares the same function, in the same Class instance, 
		// with another number of arguments, this will result in an overload when called upon
		c2.set("func_2", new Function(){
			public void run(Instance self, Instance...args){
				
				// Print the content of the first parameter
				System.out.println(args[0].m_data.toString());
			}
		}, 1);
		
		// Set instance i1 to contain the class-instance c1
		i2.m_class = c2;
		
		// Set its superclass to be i1, this will allow the program ,
		// to search the i1 for methods it the doesn't exist in i2
		i2.m_class.m_superclass = c1;
		

		invoke(i2, "func");										// Will search i1 for the function 'func' 
		invoke(i2, "func_2");									// Will run the first declared func_2, cause it doesen't have any in-arguments
		invoke(i2, "func_2", new Instance("One parameter"));	// Will run the second decoration of func_2, cause we provide one in-parameter
		
	}
	
	// Invoke function that executes method based on inparameter 'method'
	public static void invoke(Instance obj, String method, Instance...args)
	{
		// Save reference to Class instance that is expected to contain method-name in its HashMap
		Class tmp_class = obj.m_class;
		
		/* 
		 * As long as as the current HashMap doesn't contain method-name as key,
		 * Check the superclass HashMap if that one contains method-name as key
		 */
		while(!tmp_class.m_dict.containsKey(method))
		{
			
			// Move reference from tmp_class to its superclass
			tmp_class = tmp_class.m_superclass;
			
			// If tmp_class is null, the requested methodname does not exist
			if(tmp_class == null){
				System.out.println("Error");
				return;
			}
		}
		
		// On keymatch in HashMap, run get-method, that retrieves the stored function,
		// based on methodname as key in HashMap and number of arguments to stored function
		tmp_class.get(method, args.length).run(obj, args);
	}
}