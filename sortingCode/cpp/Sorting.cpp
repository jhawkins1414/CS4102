//Jared Hawkins jmh7ax 8/30/2021

#include <iostream>
#include <vector>

using namespace std;


void insertionSort(vector<int> &list, int i, int j){
	//Iterates from i+1 to j
	for(int k = i + 1; k <= j; k++){
		int key = list[k];
		int x = k - 1;
		//Finds the correct place in the list for key
		while(x >= 0 && list[x] > key){
			list[x + 1] = list[x];
			x = x-1;
		}
		list[x+1] = key;
	}
}


int partition(vector<int> &list, int i, int j) {
	//partition procedure for quicksort
	int x = list[j];
	int y = i - 1;
	for(int z = i; z < j; z++){
		if(list[z] <= x){
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

void quickSort(vector<int> &list, int i, int j, int minSize) {
	//If list is not at least minSize, call insertion sort instead
	if(minSize <= 1 || list.size() > minSize){
		//recurse on either side of partition
		if(i < j){
			int q = partition(list, i, j);
			quickSort(list, i, q-1, minSize);
			quickSort(list, q+1, j, minSize);
		}
	}
	else{
		insertionSort(list, i, j);
	}
}

