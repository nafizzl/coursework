#include "BaseSorterB.h"
#include "BubbleSorterB.h"
#include <iostream>
#include <fstream>
using namespace std;

BubbleSorter::BubbleSorter(ifstream& ifs, int size) : BaseSorter(ifs, size){
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

BubbleSorter::~BubbleSorter() {
  if(!buf) {
    delete[] buf;
  }
}

void BubbleSorter::print() {
  for(int i = 0; i < numEntries; i++) {
    cout << buf[i] << "\t";
   }
   cout << endl;
}

void BubbleSorter::sort() {
  	for (int i = 0; i < numEntries - 1; i++) {
  		for (int j = numEntries - 1; j >= i + 1; j--){
  			if (buf[j] < buf[j-1]){
  				int temp = buf[j];
  				buf[j] = buf[j-1];
  				buf[j-1] = temp;
			  }
		  }
	}
	cout << "Bubble sorting carried out..." << endl;
	cout << endl;
  } // comparisons between two consecutive numbers in an array one by one to see which is higher, which is lower

