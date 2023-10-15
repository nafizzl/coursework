#include "BaseSorterB.h"
#include <iostream>
#include <fstream>
using namespace std;

BaseSorter::BaseSorter(ifstream& ifs, int size) : numEntries(size) {
  // The input file stream may have been opened previously (e.g.
  // to find the number of entries).  We clear the input file stream
  // and set the file pointer to the beginning of the file.
  ifs.clear();
  ifs.seekg(0);
  
  buf = new int[numEntries];

  for(int i = 0; i < numEntries; i++)
    ifs >> buf[i];
}

BaseSorter::~BaseSorter() {
  if(!buf) {
    delete[] buf;
  }
}

void BaseSorter::print() {
  for(int i = 0; i < numEntries; i++) {
    cout << buf[i] << "\t";
   }
   cout << endl;
}

