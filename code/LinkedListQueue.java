public class LinkedListQueue<E> implements Queue<E>
{
	 SinglyLinkedList<E> s ;
	
	  public LinkedListQueue()
	  {
             s = new SinglyLinkedList<E>();
	  }
	  public int size()
	  {
		  return s.size();
	  }
	  public boolean isEmpty()
	  {
		  return s.isEmpty();
	  }
	  public E first()
	  {
		   E element = s.first();
		  return element;
	  }
	  public void enqueue(E node)
	  {
		     s.addLast(node);
	  }
	  public E dequeue()
	  {
              return s.removeFirst();
	  }
} 

