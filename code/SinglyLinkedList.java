//EECS 2011 - SU2020
//Group Members:
// Adrian Angara 216090532
// Nadimul Hasan 216429516
// Mahid Ahmad 216652398

public class SinglyLinkedList<E>{

	  private static class Node<E>{
		  private E val; 
		  private Node<E> next; 
	    public Node(E e, Node<E> n)
		{
		  this.val = e ;
	      this.next = n ;	  
	    }
	    public E getElement()
		{
			return this.val;
	    }
	    public Node<E> getNext()
		{
			return next; 
	    }
	    public void setNext(Node<E> n)
		{
			this.next = n ; 
	    }
	  }
	  
	  private Node<E> head;
	  private Node<E> tail; 
	  private int size; 
	  
	  public SinglyLinkedList()
	  {
		  head = null;
		  tail = null;
		  size = 0;
	  }
	  
	  public int size()
	  {
		  return size;
	  }
	  
	  public boolean isEmpty()
	  {
		  if(size == 0)
			  return true;
		  else 
			  return false;
	  }
	  
	  public E first()
	  {
		  if(isEmpty())
			  return null;
		  else
			  return head.getElement();
	  }
	  
	  public E last()
	  {
		if(isEmpty())
			return null;
		else 
			return tail.getElement();
	  }
	  
	  public void addFirst(E element)
	  {
		head = new Node<E>(element,head);
		
		if(isEmpty())
			tail = head;
		
		size++;
	  }
	  
	  public void addLast(E element)
	  {
             Node<E> n = new Node<E>(element,null);
		if(isEmpty())
		{
			head = n;
			tail = n;
		}
		else 
		{
			tail.setNext(n);
			tail = n;
		}
		size++;
	  }
	  
	  public E removeFirst()
	  {
		 if(head == null)
		   return null;

		   E temp = head.getElement();
		   head = head.getNext();
		   size--;
                 if(size == 0)
                    tail = null;

		   return temp;
	  }

	}
