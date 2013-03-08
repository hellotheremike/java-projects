package alda;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MySkiplistTest {
	//Initiates a skiplist with a maximumlevel of 4.
	MySkiplist<Integer> list = new MySkiplist<Integer>(4);

	//Populates the skiplist with 101 integers 0-100
	@Before
	public void setUp() {
		for(int x = 0; x <= 100;x++){
			list.add(new Integer(x));
		}
	}
	
	//Verifys that it doesent allow Duplicates
	@Test
	public void addDuplicates(){
		assertFalse(list.add(new Integer(0)));
		assertFalse(list.add(new Integer(10)));
		assertFalse(list.add(new Integer(100)));
		assertEquals(101, list.size());
	}
	//Attempting to remove elemements that do and do not exist in the list.
	@Test
	public void remove(){
		assertFalse(list.remove(new Integer(101)));
		assertTrue(list.remove(new Integer(0)));
		assertTrue(list.remove(new Integer(100)));
	}
	//Verify if the element exists
	@Test
	public void search(){
		assertTrue(list.contains(new Integer(0)));
		assertFalse(list.contains(new Integer(101)));
	}
	
	//Doing all the thest that are conducetd above on a CharacterSkiplist
	@Test
	public void characterTest(){
		MySkiplist<Character> listChar = new MySkiplist<Character>(4);
		listChar.add('A');
		listChar.add('B');
		listChar.add('C');
		listChar.add('D');
		listChar.add('E');
		
		assertTrue(listChar.contains('A'));
		assertFalse(listChar.contains('Z'));
		assertFalse(listChar.add('A'));
		
		assertTrue(listChar.remove('A'));
		assertEquals(4, listChar.size());
	}

}
