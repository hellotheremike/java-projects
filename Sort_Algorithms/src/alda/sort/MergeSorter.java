package alda.sort;

import java.util.List;


/**
 * Detta �r en av algoritmerna du ska implementera sj�lv. H�r f�r du, till
 * skillnad mot de �vriga fallen, anv�nda en extra array.
 * 
 * Se kursboken sidan 258 och fram�t. T�nk dock p� att implementationen i boken
 * jobbar p� arrayer, men den h�r jobbar p� en lista av godtycklig typ.
 */
public class MergeSorter<T extends Comparable<? super T>> extends Sorter<T> {

	@Override
	protected void doSort(List<T> l) {
        @SuppressWarnings("unchecked")
		T [] tmpArray = (T[]) new Comparable[ l.size() ];
        mergeSort( l, tmpArray, 0, l.size() - 1 );
	}
	
    private void mergeSort(List<T> a, T [] tmpArray, int left, int right ){
        if( left < right )
        {
            int center = ( left + right ) / 2;
            mergeSort( a, tmpArray, left, center );
            mergeSort( a, tmpArray, center + 1, right );
            merge( a, tmpArray, left, center + 1, right );
        }
    }
    private void merge( List<T> a, T[] tmpArray, int leftPos, int rightPos, int rightEnd )
    {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;
        
        while( leftPos <= leftEnd && rightPos <= rightEnd )
            if( a.get( leftPos ).compareTo( a.get( rightPos ) ) <= 0 )
                tmpArray[ tmpPos++ ] = a.get( leftPos++ );
            else
                tmpArray[ tmpPos++ ] = a.get( rightPos++ );

        while( leftPos <= leftEnd )
            tmpArray[ tmpPos++ ] = a.get( leftPos++ );

        while( rightPos <= rightEnd )
            tmpArray[ tmpPos++ ] = a.get( rightPos++ );

        for( int i = 0; i < numElements; i++, rightEnd-- )
            a.set( rightEnd, tmpArray[ rightEnd ]);
    }
}
