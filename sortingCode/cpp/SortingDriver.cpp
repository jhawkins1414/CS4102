#include <iostream>
#include <vector>
#include <unordered_map>
#include <cstdlib>
#include <chrono>
#include <algorithm>

using namespace std;
using namespace std::chrono;

void insertionSort(vector<int> &list, int start, int end);
void quickSort(vector<int> &list, int i, int j, int minSize);
bool checkSorted(vector<int> &orig, vector<int> &sorted);

/* Number of elements to sort */
int SIZE = 100;

int main() {

    // TESTNG
	/*vector<int> vect1;
	vect1.push_back(10);
	vect1.push_back(1);
	vect1.push_back(15);
	vect1.push_back(2);
	vect1.push_back(6);
	vect1.push_back(5);
	vect1.push_back(4);
	vect1.push_back(3);
	quickSort(vect1, 0, 4, 20);
	for(int i=0; i<vect1.size(); i++) {
		cout << vect1[i] << ", ";
	}
	cout << endl;
	vector<int> vect2;
	vect2.push_back(10);
	vect2.push_back(1);
	vect2.push_back(15);
	vect2.push_back(2);
	vect2.push_back(6);
	vect2.push_back(5);
	vect2.push_back(4);
	vect2.push_back(3);
	insertionSort(vect2, 0, 5);
	for(int i=0; i<vect2.size(); i++) {
		cout << vect2[i] << ", ";
	}
	cout << endl;
	vector<int> vect3;
	vect3.push_back(10);
	vect3.push_back(1);
	vect3.push_back(15);
	vect3.push_back(2);
	vect3.push_back(6);
	vect3.push_back(5);
	vect3.push_back(4);
	vect3.push_back(3);
	quickSort(vect3, 0, 7, 1);
	for(int i = 0; i < vect3.size(); i++) {
		cout << vect3[i] << ", ";
	}
	cout << endl; 	*/

	//TESTING - list with random numbers from 1 to 1,000,000

	vector<int> listTest;
	vector<int>listTest2;
	for(int i=0; i<75000; i++) listTest.push_back((int)(rand() % 1000000 + 1));
	//for(int i=0; i<75000; i++) listTest.push_back(i);
	/*for(int i=0; i<75000 i++) {
		
	}*/
/*	for(int i = 0; i < listTest.size(); i++){
		int x = i;
		if (i % 5 == 1){
			x = x + 4;
		}
	else if (i % 5 == 2){
		x = x + 2;}
		// leave it the same
	else if (i % 5 == 4){
		x = x - 2;}
	else if (i % 5 == 0){
		x = x - 4;}
	listTest[i] = x;
	listTest2.push_back(x);
	}


for(int i = 0; i < listTest2.size(); i++) {
		cout << listTest2[i] << ", ";
	}
	cout << endl;*/
	auto start = high_resolution_clock::now();
	quickSort(listTest, 0, listTest.size()-1, 30);
	auto stop = high_resolution_clock::now();
	auto duration = duration_cast<microseconds>(stop - start);
  
    cout << "Time taken by function: "
         << duration.count() << " microseconds" << endl;

	/*for(int i = 0; i < listTest2.size(); i++) {
		cout << listTest2[i] << ", ";
	}
	cout << endl;*/
	
	/* Make an array (vector) to sort. Fill with random numbers */
	vector<int> list;
	for(int i=0; i<SIZE; i++) list.push_back((int)(rand()*SIZE));
	
	/* Make copies to sort */
	vector<int> ins;
	vector<int> qui;

	ins = list; qui = list; //should make deep copies
	

	cout << "Sorting using insertion sort...";
	insertionSort(ins, 0, ins.size()-1);
	cout << "DONE\nChecking if sorted correctly...";
	checkSorted(list, ins);
	cout << "DONE" << endl;
	
	cout << "Sorting using quick sort...";
	quickSort(qui, 0, qui.size()-1, 1);
	cout << "DONE\nChecking if sorted correctly...";
	checkSorted(list, qui);
	cout << "DONE" << endl;

}

	
bool checkSorted(vector<int> &orig, vector<int> &sorted) {

	/* Make sure size is the same */
	if(orig.size() != sorted.size()) {
		cout << "ERROR...original list and sorted list have different lengths...";
		return false;
	}
	
	/* Make sure new array is sorted */
	for(int i=1; i<sorted.size(); i++) {
		if(sorted[i] < sorted[i-1]) {
			cout << "ERROR...the sorted list does not appear to be correctly sorted...";
			return false;
		}
	}
	
	/* Make sure the same elements are in each array */
	unordered_map<int, int> count;
	for(int i=0; i<orig.size(); i++) {
		if(count.count(orig[i]) == 0) count[orig[i]] = 1;
		else count[orig[i]]++;
		
		if(count.count(sorted[i]) == 0) count[sorted[i]] = -1;
		else count[sorted[i]]--;
	}
	for(auto &key : count) {
		if(key.second != 0) {
			cout << "ERROR...The sorted list does not contain the same elements that the original list does...";
			return false;
		}
	}
	
	return true;
}

/**
 * Use this printlist method while debugging if helpful
 * @param list
 */
void printList(vector<int> &list) {
	for(int i=0; i<list.size(); i++) {
		cout << list[i] << ", ";
	}
	cout << endl;
}
