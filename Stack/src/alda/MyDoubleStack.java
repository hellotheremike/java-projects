package alda;

public class MyDoubleStack {
	public String data[];
	public int StackCountA;
	public int StackCountB;

	public MyDoubleStack(int size){
		data = new String[size];
		this.StackCountB = size;
		this.StackCountA = 0;
	}
	
	public int lengthA(){
		return (StackCountA);
	}
	public int lengthB(){
		return (data.length-StackCountB);
	}
	
	public boolean pushA(String i){
		if(StackCountA < (StackCountB-1)){
			this.StackCountA++;
			data[this.StackCountA] = i;
			return true;
		}
		else{
        	System.err.println("Stackoverflow");
			return false;
		}
	}
	public boolean pushB(String i){
		if(StackCountA < (StackCountB-1)){
			this.StackCountB--;
			data[this.StackCountB] = i;
			return true;
		}
		else{
        	System.err.println("Stackoverflow");
			return false;
		}
	}
	
	public String popA(){	
        if (StackCountA < data.length && StackCountA < StackCountB && StackCountA > 0)
        {
            String str = data[StackCountA];
            StackCountA--;
            return str;
        }
        else
        {
        	System.err.println("StackA ist empty.");
            return null;
        }	
	}
	public String popB(){
        if (StackCountB < data.length && StackCountA < StackCountB)
        {
            String str = data[StackCountB];
            StackCountB++;
            return str;
        }
        else
        { 
        	System.err.println("StackB ist empty.");
            return null;
        }
	}

}
