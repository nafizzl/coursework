#include "BaseSorterB.h"
#include "InsertionSorterB.h"
#include <iostream>
#include <fstream>
using namespace std;

InsertionSorter::InsertionSorter(ifstream& ifs, int size) : BaseSorter(ifs, size){
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

InsertionSorter::~InsertionSorter() {
  if(!buf) {
    delete[] buf;
  }
}

void InsertionSorter::print() {
  for(int i = 0; i < numEntries; i++) {
    cout << buf[i] << "\t";
   }
   cout << endl;
}

void InsertionSorter::sort() {
   int i, j, key;
   for(i = 1; i < numEntries; i++) {
      key = buf[i];
      for(j = i; j > 0 and buf[j-1] > key; j--) {
         buf[j] = buf[j-1];
      }
      buf[j] = key;  
   }
	cout << "Insertion sorting carried out..." << endl; 
	cout << endl;
  } // implements multiple comaprisons to place numbers in their correct indices in the number array

