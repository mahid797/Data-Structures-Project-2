//EECS 2011 - SU2020
//Group Members:
// Adrian Angara 216090532
// Nadimul Hasan 216429516
// Mahid Ahmad 216652398

public interface Queue<E>{
  int size();
  boolean isEmpty();
  E first();
  void enqueue(E node);
  E dequeue();
}

