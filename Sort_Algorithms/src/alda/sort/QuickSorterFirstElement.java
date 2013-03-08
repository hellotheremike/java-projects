package alda.sort;

import java.util.List;

/**
 * Detta är en av algoritmerna du ska implementera själv. Algoritmen ska vara
 * quicksort, och pivotvärdet ska alltid vara det första värdet, det som på
 * sidan 265 i boken förklaras vara "a wrong way".
 * 
 * För information om quicksort generellt: se kursboken sidan 264 och framåt.
 * Tänk dock på att implementationen i boken jobbar på arrayer, men den här
 * jobbar på en lista av godtycklig typ och att den använder en annan
 * pivotvalsstrategi vilket kräver att man skriver om den lite grann.
 */
public class QuickSorterFirstElement<T extends Comparable<? super T>> extends Sorter<T> {
	
	private static final int CUTOFF = 10;

	private void insertionSort(List<T> l, int left, int right) {
		int j;
		for (int p = left + 1; p <= right; p++) {
			T tmp = l.get(p);
			for (j = p; j > left && tmp.compareTo(l.get(j - 1)) < 0; j--) {
				l.set(j, l.get(j - 1));
			}
			l.set(j, tmp);
		}
	}
	
	private void quicksort(List<T> l, int left, int right){
		if (left + CUTOFF <= right) {	
			int low = left;
			int mid = left;
			int high = right;
			T pivot = l.get(left);
			
			while(mid <= high){
				T a = l.get(mid);
		        if (pivot.compareTo(a) > 0) {
		            swap(l, mid, low);
		            low++;
		            mid++;
		        } else if (a == pivot) {
		            mid++;
		        } else {
		            swap(l, mid, high);
		            high--;
		        }
			}
			quicksort(l, low + 1, right);
			quicksort(l, left, low - 1);
		}	
		else {
			insertionSort(l, left, right);
		}
	}

	@Override
	protected void doSort(List<T> l) {
			quicksort(l, 0, l.size() - 1);
	}


}
