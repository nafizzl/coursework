#ifndef BASESORTER_H
#define BASESORTER_H

#include <fstream>
using namespace std;

class BaseSorter {
public:
  BaseSorter(ifstream& ifs, int size);
  
  virtual ~BaseSorter();
  
  void print();

  virtual void sort() = 0;

protected:
  int *buf;
  int numEntries;
};

#endif // BASESORTER_H