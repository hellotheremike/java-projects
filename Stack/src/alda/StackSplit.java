package alda;
import java.util.*;

public class StackSplit {
	private Stack<Integer> stack_1 = new Stack<Integer>();
	private Stack<Integer> stack_2 = new Stack<Integer>();
	private ArrayList<Element> container;

	public StackSplit(int arrSize){
		this.container = new ArrayList<Element>();
		for(int i = 0; i < arrSize; i++){
			this.container.add(new Element(new Integer(i)));		
		}
	}
	public StackSplit(ArrayList<Element> array){
		this.container = new ArrayList<Element>();
		this.container = array;
	}
	
	private static class Element{
		public int data;
		public boolean TAKEN;
		
		public int getData(){
			this.TAKEN = true;
			return this.data;
		}
		
		public Element(int data) {
			this.data = data;
			this.TAKEN = false;
		}
	}

	public boolean buildStacks(){
		int size = this.container.size();
		if(size % 2 != 0){
			size = (size/2)+1;
		}
		else{
			size = (size/2);
		}
		
		for(int i=0; i < size; i++){
			int end = (this.container.size()-i)-1;
			if(this.container.get(i).TAKEN == false){
				this.stack_1.push(this.container.get(i).getData());
			}
			if(this.container.get(end).TAKEN == false){
				this.stack_2.push(this.container.get(end).getData());
			}
		}
		return true;
	}
	
	public void getStacks(){
		System.out.println("Stack one: " +this.stack_1);
		System.out.println("Stack two: " +this.stack_2);
	}
	
}
