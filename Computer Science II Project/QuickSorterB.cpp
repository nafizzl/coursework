#include "BaseSorterB.h"
#include "QuickSorterB.h"
#include <iostream>
#include <fstream>
using namespace std;

QuickSorter::QuickSorter(ifstream& ifs, int size) : BaseSorter(ifs, size){
  // The input file stream may have been opened previously (e.g.
  // to find the number of entries).  We clear the input file stream
  // and set the file pointer to the beginning of the file.
  ifs.clear();
  ifs.seekg(0);
  
  numEntries = size;
  buf = new int[numEntries];

  for(int i = 0; i < numEntries; i++)
    ifs >> buf[i];
}

QuickSorter::~QuickSorter() {
  if(!buf) {
    delete[] buf;
  }
}

void QuickSorter::print() {
  for(int i = 0; i < numEntries; i++) {
    cout << buf[i] << "\t";
   }
   cout << endl;
}

void QuickSorter::sort() {
	sort(0, numEntries - 1); // to conduct recursion, I needed a way to implement sort with arguments representing array indices so that the "divide and conquer" method would actually work out
  }
  
void QuickSorter::sort(int start, int end){
    if (start < end){
    int p = partition(start, end);
    sort(start, p - 1);
    sort(p + 1, end);
    }
} // sort which implements sort recursively

int QuickSorter::partition(int start, int end){
    int x = buf[end];
    int i = start - 1;
    for (int j = start; j < end; j++){
        if (buf[j] <= x){
            i++;
            int temp = buf[i];
  				buf[i] = buf[j];
  				buf[j] = temp;
        }
    }
    int ph = buf[i + 1];
  	buf[i + 1] = buf[end];
  	buf[end] = ph;
  	return i + 1;
}// partition which divides up the array into smaller arrays and places numbers in their correct position
