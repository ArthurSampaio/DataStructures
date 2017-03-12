package sorting;

import util.Util;

public class RecursiveBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(array == null) return;
	   
		if(leftIndex < 0) leftIndex = 0;
		   
	   
		if(rightIndex > array.length -1)
		   rightIndex = array.length -1;
		
		rightIndex = Util.mandaNullParaOFim(array, leftIndex, rightIndex);
		   
		bubble(array, leftIndex, rightIndex); 
		
	}
	
	
	private void bubble(T[] array, int left, int right){
		if (array == null)
			return;
		
		if(right > left){
			for(int i = left; i<right;i++){
				if(array[i].compareTo(array[i+1])>0){
					Util.swap(array, i, i+1);
				}
				
				if((right-i >left) && (array[right-i].compareTo(array[right -i -1])<0))
					Util.swap(array, right - i, right -i -1);
			}bubble(array, left+1, right-1);
		}
		
		
		
	}
	

}
