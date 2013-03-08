package alda;

public class main {

	 public static void main(String[] args){ 

		 /*MyDoubleStack stack = new MyDoubleStack(10);
		 stack.pushA("Penna");
		 stack.pushA("Linjal");
		 stack.pushB("Tulpan");
		 stack.pushB("Ros");
		 
		 System.out.println(stack.popA());
		 System.out.println(stack.popA());
		 System.out.println(stack.popA());
		 
		 System.out.println(stack.popB());
		 System.out.println(stack.popB());
		 System.out.println(stack.popB());*/

		 int n = 100;
		 int sum = 0 ;
		 long startTime = System.nanoTime();    
		 for (int i = 0 ; i < n ; i++)
			 sum++;
		 long estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel1: " + estimatedTime);
		 
   
		 sum = 0 ;
		 startTime = System.nanoTime();
		 for (int i = 0 ; i < n ; i++)
			 for (int j = 0 ; j < n ; j++)
				 sum++;
		 estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel2: " + estimatedTime);
		 


		 sum = 0 ;
		 startTime = System.nanoTime();
		 for (int i = 0 ; i < n ; i++)//o(n)
			 for (int j = 0 ; j < n * n ; j++)//o(n)
				 sum++;//o(1)
		 
		 estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel3: " + estimatedTime);
		 
		 sum = 0 ;
		 startTime = System.nanoTime();
		 for (int i = 0 ; i < n ; i++)//o(n)
			 for (int j = 0 ; j < i ; j++)//o(n)
				 sum++;//o(1)
		//Total: o(n^2)
		 estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel4: " + estimatedTime);
		 
		 sum = 0 ;
		 startTime = System.nanoTime();
		 for (int i = 0 ; i < n ; i++)//o(n)
			 for (int j = 0 ; j < i * i ; j++)//o(n)
				 for (int k = 0 ; k < j ; k++)//o(n)
					 sum++;//o(1)
		//Tot: o(n^3)
		 estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel5: " + estimatedTime);
		 
		 sum = 0 ;
		 startTime = System.nanoTime();
		 for (int i = 1 ; i < n ; i++)
			 for (int j = 1 ; j < i * i ; j++)
				 if (j % i == 0)
					 for (int k = 0 ; k < j ; k++)
						 sum++;
		 estimatedTime = System.nanoTime() - startTime;
		 System.out.println("Exempel6: " + estimatedTime);

	}

}
