package alda.sort;
import java.util.List;
import java.util.Random;
public class QuickSorterRandom<T extends Comparable<? super T>> extends Sorter<T> {

	static Random r = new Random();
	
	private void insertionSort(List<T> l, int left, int right) {
		int b;
		for (int p = left; p < right; p++) {
			T tmp = l.get(p);
			for (b = p; b > left && tmp.compareTo(l.get(b - 1)) < 0; b--) {
				l.set(b, l.get(b - 1));
			}
			l.set(b, tmp);
		}
	}
	
	protected void doSort(List<T> l) {
		quicksort (l, 0, l.size());
	}
	
	private void quicksort(List<T> l, int left, int right) {
		//Om listan ar kort gar det snabbare att gora en Insertion sort
		if (left + 20 >= right) {
			
			T pivot = l.get(r.nextInt(right-left));
			
			int a = left; int b = right - 1;
			while (true) {
				//Lat a och b fa forbi pivot uppat resp. nedat
				while (l.get(a++).compareTo(pivot) < 0) { }
				while (l.get(b--).compareTo(pivot) > 0) { }
				//Om elementet a ar storre an b...  
				if ( a > b) {
					//...byter vi plats pa dem.
					swap (l, a, b);
				} else {
					break;
				}
			}//while
			
			swap(l, a, right - 1);
			
			//Utfor samma operation pa en dellista,
			//baserad pa a:s position efter pivot
			quicksort(l, left, a - 1);
			quicksort(l, a, right);
		} else {
			insertionSort(l, left, right);
		}
	}//quicksort
}
