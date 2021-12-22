
import java.util.HashMap;

public class Sorting {

	public static<T extends Comparable<T>> void insertionSort(Integer[] list, int i, int j) {
		//TODO: IMPLEMENT THIS METHOD
		for(int k = i + 1; k <= j; k++){
			int key = list[k];
			int x = k - 1;
			while(x >= 0 && list[x].compareTo(key) > 0){
				list[x + 1] = list[x];
				x = x - 1;
			}
			list[x + 1] = key;
		}
	}
	
	public static<T extends Comparable<T>> void quickSort(Integer[] list, int i, int j, int minSize) {
		//TODO HELPER METHOD FOR QUICKSORT THAT TAKES IN INDICES
		if(minSize <= 1 || list.length > minSize){
			//recurse on either side of partition
			if(i < j){
				int q = partition(list, i, j);
				quickSort(list, i, q-1, list.length);
				quickSort(list, q+1, j, list.length);
			}
		}
		else{
			insertionSort(list, i, j);
		}
	}
	
	private static<T extends Comparable<T>> int partition(Integer[] list, int i, int j) {
		//TODO: PARTITION THE PORTION OF THE LIST BETWEEN i AND j
		int x = list[j];
		int y = i - 1;
		for(int z = i; z < j; z++){
			if(list[z].compareTo(x) <= 0){
				y = y + 1;
				int temp = list[y];
				list[y] = list[z];
				list[z] = temp;
			}
		}
		int tempA = list[y+1];
		list[y+1] = list[j];
		list[j] = tempA;
		return y + 1;
	}

}
