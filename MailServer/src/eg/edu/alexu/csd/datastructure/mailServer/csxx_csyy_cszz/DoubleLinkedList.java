package eg.edu.alexu.csd.datastructure.linkedList.csxx_csyy;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

public class DoubleLinkedList implements ILinkedList {
	public Dnode head ;
	public Dnode tail ;
	public static class Dnode{
		Object element ;
		public Dnode next ;
		public Dnode prev ;
		Dnode(Object data){
			element = data ;
			next = prev = null ;
		
		}
	}
	
	int length = 0 ;
	
	
	public void add(int index, Object element) {
		int count = 0 ;
		Dnode newnode = new Dnode(element) ;
		Dnode temp = head ; 
		if(index == 0 ) {
			newnode.next = head;
			head = newnode;
			newnode.prev = null;
			head.prev = newnode ;
			length ++ ;
		}
		else if(index >=0 && index < length){
			while(temp.next != null) {
			if(count== index) {	
				newnode.next = temp.next ;
				temp.next.prev = newnode ;
				temp.next = newnode ;
				newnode.prev=temp ;
				break ;
			}	
			count ++ ; 
			temp = temp.next ;
			
		}
			length ++ ;
		}
		if(newnode.next==null){
			tail = newnode; 
			length ++ ;
		}
		
		else {
			throw new RuntimeException();
		}
		
	}
	
	
	public void add(Object element) {
		Dnode newnode = new Dnode(element) ;
		
		if(head == null) {
			newnode.prev = null ;
			head = newnode;
			tail = newnode ;
		}
		else {
			tail.next = newnode ;
			newnode.prev = tail ;
			tail = newnode ;
		}
		
		length ++ ;
	}
	public Object get(int index) {
		int count= 0 ;
		
		if(index == 0 ) {
			 return head.element ;
		}
		
		else if(index >= 0 && index < length  ) {
		Dnode temp = head ;
		while(temp != null) {
			if(count == index) {
				return temp.element ;
			}
			temp = temp.next ; 
			count++ ;
			}
		}
		
		throw new RuntimeException();
			
		
	}
	public void set(int index, Object element) {
		Dnode newnode = new Dnode(element) ;
		Dnode temp = head ;
		int count = 0 ;
		if(index == 0) {
			newnode.next = temp.next ;
			head = newnode ;
			head.next.prev = newnode ;
			newnode.prev = null ;
						
		}
		else if(index >= 0 && index < length) {
			while(temp.next != null) {
				if(count == index) {
					temp.prev.next = newnode ;
					newnode.next = temp.next ;
					temp.next.prev = newnode ;
					newnode.prev = temp.prev ;
					break ;
				}
				temp = temp.next ;
				count ++ ;
			}
		}
		else {
			throw new RuntimeException();
		}
		
	}
	public void clear() {
		
		head = null ;
		tail = null ;
		length = 0 ;
	}
	
	public boolean isEmpty() {
		if(length == 0) {
			return true ;
		}
		else {
			return false ;
		}
	}
	
	public void remove(int index) {
		
		int count =0 ;
		Dnode temp = head ;
		Dnode temp2 = tail ;
		
		if(index == 0) {
			head = temp.next ;
			head.prev = null ;
			length -- ;
		}
		else if(index == length-1) {
			tail = temp2.prev ;
			tail.next = null ; 
			length -- ;

		}
		
		else if(index >= 0 && index < length ) {
			
				while(count < index) {
					
					temp = temp.next ;
					count++ ;
				}
				
				temp.next.prev = temp.prev ;
				temp.prev.next = temp.next ;
		
			length -- ;		
		}
		else {
			throw new RuntimeException();

		}
		
	}
	public int size() {
		return length ;
	
	}
	public ILinkedList sublist(int fromIndex, int toIndex) {
		
		if(fromIndex<0 || toIndex>=length || fromIndex>toIndex)
	
		{
			throw new RuntimeException();
		}
		Dnode temp = head;
		DoubleLinkedList newList = new DoubleLinkedList();

		for (int count = 0; count <= toIndex; count++) {
			
			if (count <= toIndex && count >= fromIndex) {
				newList.add(temp.element); 
			}
			temp = temp.next;
		}
		
		return newList;
	}
	
	public boolean contains(Object o) {
		
		Dnode temp = head ;
		if(o == null) {
			return false ;
		}
		while(temp != null) {
			if(temp.element == o ) {
				return true ;
			}
			temp = temp.next ;
	
		}
		return false ;
	}
	
	
	public void print() {
		Dnode temp = head ;
		while(temp != null) {
			System.out.println(temp.element);
			temp = temp.next ;
		}
	}
	public Dnode getnode(int index) {
		int count= 0 ;
		
		if(index == 0 ) {
			 return head ;
		}
		
		else if(index >= 0 && index < length  ) {
		Dnode temp = head ;
		while(temp != null) {
			if(count == index) {
				return temp ;
			}
			temp = temp.next ; 
			count++ ;
			}
		}
		
		throw new RuntimeException();
			
		
	}
	public void swapnodes(DoubleLinkedList l , int start ,int end) {
		if(start == end) {
			return ;
		}
		
		Object temp = l.get(start);
		l.getnode(start).element = l.get(end) ;
		l.getnode(end).element = temp ;
		
	}
	public void reverse() { 
        Dnode temp = null; 
        Dnode current = head; 
  
        
        while (current != null) { 
            temp = current.prev; 
            current.prev = current.next; 
            current.next = temp; 
            current = current.prev; 
        } 
  
        
        if (temp != null) { 
            head = temp.prev; 
        } 
    } 
	
	
	
}




