package alda;

import static org.junit.Assert.*;

import org.junit.*;

/**
 * B�rjan p� en testsvit f�r borttag ur ett AVL-tr�d. Testar inte alla
 * rotationstyper, s� vill ni vara hyfsat s�kra p� att er kod fungerar b�r ni
 * ut�ka testfallen.
 */
public class AvlTreeTester {

	private AvlTree<Integer> tree = new AvlTree<Integer>();

	private void checkTree(int expectedHeight, int expectedSize,
			int expectedMin, int expectedMax) {
		assertEquals(expectedSize, tree.size());
		assertEquals(new Integer(expectedMin), tree.findMin());
		assertEquals(new Integer(expectedMax), tree.findMax());
		//assertTrue(tree.hasCorrectHeightInfo());
		//assertTrue(tree.isSearchTree());
	}

	@Test
	public void basicAddAndRemove() {

		assertTrue(tree.isEmpty());
		assertEquals(0, tree.size());

		// Testa att ta bort ur ett tomt tr�d
		tree.remove(1);

		// H�jden av ett tomt tr�d �r -1
		assertEquals(-1, tree.maxHeight());

		//assertTrue(tree.hasCorrectHeightInfo());
		//assertTrue(tree.isSearchTree());

		tree.insert(1);
		checkTree(0, 1, 1, 1);
		assertFalse(tree.isEmpty());
		tree.insert(2);
		checkTree(1, 2, 1, 2);
		tree.insert(3);
		checkTree(1, 3, 1, 3);

		for (int n = 4; n <= 10; n++) {
			tree.insert(n);
			checkTree(3, n, 1, n);
		}

		// S�tt in en dublett, ska inte ge n�gon f�r�ndring
		tree.insert(5);
		checkTree(3, 10, 1, 10);

		tree.remove(1);
		checkTree(3, 9, 2, 10);
		assertFalse(tree.isEmpty());

		// Testa att ta bort ett element som inte finns
		tree.remove(1);
		checkTree(3, 9, 2, 10);

		tree.remove(2);
		checkTree(3, 8, 3, 10);

		tree.remove(9);
		checkTree(2, 7, 3, 10);

		tree.insert(1);
		checkTree(3, 8, 1, 10);

		tree.insert(0);
		checkTree(3, 9, 0, 10);

		tree.remove(5);
		checkTree(3, 8, 0, 10);

		// H�r b�r ni ut�ka testen s� att alla rotationersvarianter testas

	}

	@Test
	public void random() {
		
		tree.insert(31);
		tree.insert(67);
		tree.insert(5);
		tree.insert(12);
		tree.insert(28);
		tree.insert(29);
		tree.insert(30);
		tree.insert(8);
		tree.insert(4);
		tree.insert(47);
		tree.insert(89);
		tree.insert(25);
		tree.insert(245);
		
		tree.insert(235);
		tree.insert(665);
		tree.insert(668);
		tree.insert(768);
		tree.insert(3);
		tree.insert(6);
		tree.insert(7);
		//tree.printTree();
		//System.out.println("Size: " + tree.size()+"\n");
		tree.remove(47);
		tree.remove(89);
		tree.remove(28);
		tree.remove(235);
		//tree.printTree();
		//System.out.println("Size: " + tree.size());
		tree.returnRoot();
		
//		for(int i = 0; i < 500; i++){
//			tree.insert(i);
//		}
//		tree.printTree();
//		tree.remove(31);
	}
}
