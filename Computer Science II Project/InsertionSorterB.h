#ifndef INSERTIONSORTERB_H
#define INSERTIONSORTERB_H

#include <fstream>
using namespace std;

class InsertionSorter : virtual public BaseSorter {// specified child/parent relation and virtuality
public:

  InsertionSorter(ifstream& ifs, int size);// changed the constructor as necessary
  
  ~InsertionSorter();
  
  void print();

  void sort();

protected:
  int *buf;
  int numEntries;
};

#endif // INSERTIONSORTERB_H