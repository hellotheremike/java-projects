package inheritance;

/* Represents the base of what each function must have
 * Needs to override the run method to specify what the function does
 */
abstract class Function
{
	// Saves the number of expected in-parameters
	public int params;
	
	// Method that runs when asked upon in invoke (main->invoke) 
	public abstract void run(Instance self, Instance...args);
}